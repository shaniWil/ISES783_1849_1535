package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/** This class represents a geometric body of sphere type
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Sphere extends RadialGeometry {
    private final Point center;

    /** Constructor to initialize Sphere based object by his radius and center point
     * @param radius the radius
     * @param center the center point of the sphere */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /** Get the center point method
     * @return     the center of the Sphere  */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point rayP0 = ray.getP0(); //( -3,0,0)
        Vector rayDir = ray.getDir().normalize(); // (0,0,1)

        if (rayP0.equals(center))
            return List.of(new GeoPoint(this,(rayP0.add(rayDir.scale(radius)))));

        Vector u = center.subtract(rayP0); // (1,0,0)
        double tm = alignZero(u.dotProduct(rayDir)); // (0,0,0)
        double d = alignZero(Math.sqrt(u.lengthSquared()- tm * tm)); // 1
        if (d >= radius)
            return null;
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0 && !isZero(t1) && !isZero(t2))
            return List.of(new GeoPoint(this,(rayP0.add(rayDir.scale(t1)))),(new GeoPoint(this,(rayP0.add(rayDir.scale(t2))))));

        if (t1 > 0 && !isZero(t1))
            return List.of(new GeoPoint(this,(rayP0.add(rayDir.scale(t1)))));

        if (t2 > 0 && !isZero(t2))
            return List.of(new GeoPoint(this,rayP0.add(rayDir.scale(t2))));

        return null;
    }
}
