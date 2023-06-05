package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static java.lang.Math.abs;
import static java.lang.Math.nextUp;
import static primitives.Util.alignZero;

/**
 * Implementation of the abstract class RayTracerBase.
 *
 * @author Raaya Feldmar & Shani Wilamowsky.
 */

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;

    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n,  double nl) {
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null || intersections.isEmpty())
            return true;
        double distance = light.getDistance(gp.point);
        Vector direction = light.getL(gp.point);
        for (GeoPoint geoIntersection : intersections) {
            Vector directionIntersection = light.getL(geoIntersection.point);
            if (light.getDistance(geoIntersection.point) < distance && directionIntersection.equals(direction))
                return false;
        }
        return true;
    }
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionGeoPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionGeoPoints == null)
            return this.scene.background;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionGeoPoints);
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

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray)
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
                if(unshaded(geoPoint,lightSource,l,n,nl)) {
                    Color iL = lightSource.getIntensity(geoPoint.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            calcSpecular(material.kS, l, n, v, material.kShininess, iL));
                }
            }
        }
        return color;
    }

    /**
     * calculating the color of a specific point, taking into account the lightning,
     * transparency of the point itself and other affects of the surrounding are of the point in space
     *
     * @param point calculate the color of this point
     * @return for now - the ambient light's intensity
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }
}
