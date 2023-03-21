package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** This class represents a radial geometric body of tube type and will serve all classes based on tube
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Tube extends RadialGeometry implements Geometry {

    /** The cylinder axis */
    protected final Ray axisRay;

    /** Constructor to initialize Tube based object with its radius and axis ray
     * @param radius the radius of the cylinder
     * @param axisRay the cylinder axis */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /** Get axis method
     * @return     the axis of the tube  */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
