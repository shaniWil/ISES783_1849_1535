package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** This class represents a geometric body of cylinder type
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Cylinder  extends Tube {

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
        Point p0=axisRay.getP0();
        Vector v=axisRay.getDir();
        if ((point.equals(p0.add(v.scale(height)))) || (point.subtract(p0.add(v.scale(height))).dotProduct(v)==0))
            return v;

        if ((point.equals(p0))||(point.subtract(p0).dotProduct(v)==0))
            return v.scale(-1);

        return super.getNormal(point);

    }

}
