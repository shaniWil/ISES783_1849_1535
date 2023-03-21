package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** This class represents a geometric body of cylinder type
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Cylinder  extends Tube implements Geometry {

    private final double height;

    /** Constructor to initialize Cylinder based object with its radius, axis ray and height.
     * @param radius the radius of the cylinder
     * @param axisRay the cylinder axis
     * @param height the height of the cylinder */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /** Get height method
     * @return     the height of the Cylinder  */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
