package geometries;

import primitives.Point;
import primitives.Vector;

/** This abstract class will serve all radial geometric bodies
 * @author Raaya Feldmar & Shani Wilamowsky */
public abstract class RadialGeometry extends Geometry {

    /** All radial geometries have a radius*/
    protected final double radius;

    /** Constructor to initialize RadialGeometry based object with radius
     * @param radius the radius */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
