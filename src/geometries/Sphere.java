package geometries;

import primitives.Point;
import primitives.Vector;

/** This class represents a geometric body of sphere type
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Sphere extends RadialGeometry implements Geometry {
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

    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
}
