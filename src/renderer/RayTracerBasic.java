package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static java.lang.Math.abs;
import static java.lang.Math.nextUp;
import static primitives.Util.*;

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
    private static final int AMOUNT=16;


    /**
     * constructor of RayTracerBasic
     * @param scene
     */
    public RayTracerBasic(Scene scene) {super(scene,false);}
    public RayTracerBasic(Scene scene, Boolean onAdaptive) {
        super(scene, onAdaptive);
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

    private Color calcColorBeamRay(Ray ray, Vector n, int level, Double3 k, Double3 kx, double spreading)
    {
        if (spreading==0)
            return calcColorGlobalEffect(ray,level,k,kx);
        Point point = ray.getP0();
        Vector vector = ray.getDir().normalize();
        if (kx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        Vector vectorWidth;
        Vector vectorLength;
        double random = spreading/AMOUNT;
        Color color = new Color(0,0,0);
        if ((vector.equals(Vector.Y))||vector.equals(Vector.Y.scale(-1))) {
            vectorLength=Vector.Z;
            vectorWidth=Vector.X;
        }
        else {
            Vector temp = Vector.Y.crossProduct(vector);
            vectorLength = vector.crossProduct(temp).normalize();
            vectorWidth = (vectorLength.crossProduct(vector)).normalize();
        }
        if (onAdaptive)
            return beamRaysOnAdaptive(ray,vectorLength,vectorWidth,spreading,level,k,kx);
        double random1;
        double random2;
        for (double i=-spreading/2; i<=spreading/2;i+=spreading/(AMOUNT-1))
        {
            for (double j=-spreading/2; j<=spreading/2;j+=spreading/(AMOUNT-1))
            {
                random1 = random(-random,random);
                while (isZero(random1  +i))
                    random1 = random(-random,random);
                random2 = random(-random,random);
                while (isZero(random2 + j))
                    random2 = random(-random,random);
                color = color.add(calcColorGlobalEffect(new Ray(point,vector.add(vectorWidth.scale(i+random1))//
                        .add(vectorLength.scale(j+random2)),n),level,k,kx));
            }
        }
        color = color.reduce(AMOUNT*AMOUNT);
        return color;
    }

    private Color beamRaysOnAdaptive (Ray ray, Vector vectorLength, Vector vectorWidth, double spearing, int level, Double3 k, Double3 kx)
    {
        double random = spearing/AMOUNT;
        Point point = ray.getP0();
        Vector vector= ray.getDir();
        Ray temp = new Ray(point,vector.add(vectorWidth.scale(-spearing/2+random(-random,random))).add(vectorLength.scale(-spearing/2+random(-random,random))));
        Ray.RayColor rayColor1 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(spearing/2+random(-random,random))).add(vectorLength.scale(-spearing/2+random(-random,random))));
        Ray.RayColor rayColor3 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(-spearing/2+random(-random,random))).add(vectorLength.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor7 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(spearing)).add(vectorLength.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor9 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));
        return calcBeamRaysOnAdaptive(rayColor1, rayColor3,rayColor7,rayColor9,vectorLength,vectorWidth,spearing,level,k,kx);
    }

    private Color calcBeamRaysOnAdaptive (Ray.RayColor rayColor1, Ray.RayColor rayColor3, Ray.RayColor rayColor7, Ray.RayColor rayColor9, Vector vectorLength, Vector vectorWidth, double spearing, int level, Double3 k, Double3 kx)
    {
        if(rayColor1.color.sameColor(rayColor3.color,rayColor7.color, rayColor9.color))
                return rayColor1.color;
        if(spearing<0.01)
            return rayColor1.color.add(rayColor3.color).add(rayColor7.color).add(rayColor9.color).reduce(4);
        double random = spearing/AMOUNT;
        Point point = rayColor1.ray.getP0();
        Vector vector= rayColor1.ray.getDir();

        Ray temp = new Ray(point,vector.add(vectorWidth.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor2 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorLength.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor4 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(spearing/2+random(-random,random))).add(vectorLength.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor5 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(spearing+random(-random,random))).add(vectorLength.scale(spearing/2+random(-random,random))));
        Ray.RayColor rayColor6 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        temp = new Ray(point,vector.add(vectorWidth.scale(spearing/2+random(-random,random))).add(vectorLength.scale(spearing+random(-random,random))));
        Ray.RayColor rayColor8 = new Ray.RayColor(temp,calcColorGlobalEffect(temp,level,k,kx));

        Color color = calcBeamRaysOnAdaptive(rayColor1, rayColor2, rayColor4, rayColor5,vectorLength,vectorWidth,spearing/2,level,k,kx);
        color = color.add(calcBeamRaysOnAdaptive(rayColor2, rayColor3, rayColor5, rayColor6,vectorLength,vectorWidth,spearing/2,level,k,kx));
        color = color.add(calcBeamRaysOnAdaptive(rayColor4, rayColor5, rayColor7, rayColor8,vectorLength,vectorWidth,spearing/2,level,k,kx));
        color = color.add(calcBeamRaysOnAdaptive(rayColor5, rayColor6, rayColor8, rayColor9,vectorLength,vectorWidth,spearing/2,level,k,kx));

        return color.reduce(4);
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
        //return calcColorGlobalEffect(constructReflectedRay(geoPoint.point, v, n),level, k,material.kR).add(calcColorGlobalEffect(constructRefractedRay(geoPoint.point, v, n),level,k,material.kT));
        return calcColorBeamRay(constructReflectedRay(geoPoint.point, v, n), n, level, k, material.kR, material.spreadingR)//
        .add(calcColorBeamRay(constructRefractedRay(geoPoint.point, v, n), n, level, k, material.kT, material.spreadingT));
    }
    private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
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
