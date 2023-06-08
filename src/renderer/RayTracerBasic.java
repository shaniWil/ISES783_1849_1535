package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static java.lang.Math.abs;
import static java.lang.Math.nextUp;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Implementation of the abstract class RayTracerBase.
 *
 * @author Raaya Feldmar & Shani Wilamowsky.
 */

public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final Double3 MIN_CALC_COLOR_K = new Double3(0.001);
    private static final Double3 MIN_KTR = new Double3(0.001);
    private static final double INITIAL_K = 1.0;

    /**
     * constructor of RayTracerBasic
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector l, Vector n,  double nv) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null || intersections.isEmpty())
            return true;
        double distance = light.getDistance(geoPoint.point);
        Vector direction = light.getL(geoPoint.point).normalize();
        for (GeoPoint geoIntersection : intersections) {
            Vector directionIntersection = light.getL(geoIntersection.point).normalize();
            if ((light.getDistance(geoIntersection.point) < distance) && (directionIntersection.dotProduct(direction) > 0))
                if ((geoIntersection.geometry.getKt().equals(new Double3(0))))
                    return false;
        }
        return true;
    }

    private Double3 transparency(GeoPoint geoPoint, LightSource light, Vector l, Vector n){
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null || intersections.isEmpty())
            return Double3.ONE;
        Double3 Ktr = Double3.ONE;

        double distance = light.getDistance(geoPoint.point);
        Vector direction = light.getL(geoPoint.point).normalize();
        for (GeoPoint geoIntersection : intersections) {
            Vector directionIntersection = light.getL(geoIntersection.point).normalize();
            if ((light.getDistance(geoIntersection.point) < distance) && (directionIntersection.dotProduct(direction) > 0))
                Ktr= Ktr.product(geoIntersection.geometry.getKt());
                if (Ktr.lowerThan(MIN_KTR))
                    return Ktr;
        }
        return Ktr;
    }



    @Override
    public Color traceRay(Ray ray) {
        GeoPoint geoPoint = findClosestIntersection(ray);
        if (geoPoint == null)
            return this.scene.background;
        return calcColor(geoPoint, ray);
    }

    private Double3 calcDiffusive(Material material, double nl)
    {
        return  material.kD.scale(abs(nl));
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n))).normalize();    // the specular ray

        // the phong model formula for the specular effect: ks ∙ ( 𝒎𝒂𝒙 (𝟎, −𝒗 ∙ 𝒓) ^ 𝒏𝒔𝒉 ) ∙ 𝑰
        return lightIntensity
                .scale(ks.scale(alignZero(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)),
                        nShininess))));
    }

    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        Vector ray = v.subtract(n.scale(2 * v.dotProduct(n))).normalize();
        return new Ray(point, ray,n);
    }

    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        Vector ray = v.normalize();
        return new Ray(point, ray, n);
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k)
    {
        Color color= geoPoint.geometry.getEmission();
        Vector v= ray.getDir();
        Vector n= geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(geoPoint, lightSource, l, n);
                if ( MIN_CALC_COLOR_K.lowerThan(ktr.product(k)) ) {
                    Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            calcSpecular(material.kS, l, n, v, material.kShininess, iL));
                }
            }
        }
        return color;
    }

    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Material material = geoPoint.geometry.getMaterial();
        return calcColorGlobalEffect(constructReflectedRay(geoPoint.point, v, n),level, k, material.kR).add(calcColorGlobalEffect(constructRefractedRay(geoPoint.point, v, n),level, k, material.kT));
    }
    private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK : calcColor(gp, ray, level-1, kkx).scale(kx);
    }


    /**
     * calculating the color of a specific point, taking into account the lightning,
     * transparency of the point itself and other affects of the surrounding are of the point in space
     *
     * @param geoPoint calculate the color of this point
     * @param ray the ray that the geoPoint on it.
     * @return for now - the ambient light's intensity
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        if (1 == level)
            return color;
        return color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray)
    {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(scene.ambientLight.getIntensity());
    }
}
