package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * Implementation of the abstract class RayTracerBase.
 *
 * @author Raaya Feldmar & Shani Wilamowsky.
 */

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionGeoPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionGeoPoints == null)
            return this.scene.background;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionGeoPoints);
        return calcColor(geoPoint);
    }

    /**
     * calculating the color of a specific point, taking into account the lightning,
     * transparency of the point itself and other affects of the surrounding are of the point in space
     *
     * @param point calculate the color of this point
     * @return for now - the ambient light's intensity
     */
    private Color calcColor(GeoPoint geoPoint) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }
}
