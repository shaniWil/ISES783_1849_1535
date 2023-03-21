package geometries;

import primitives.Point;
import primitives.Vector;

/** This interface will serve all geometric bodies.
 * @author Raaya Feldmar & Shani Wilamowsky */
public interface Geometry {

    /** Receives a point on the surface of the geometric body
     * and returns the normal vector to the body at this point.
     * @param point on the surface
     * @return     the vertical vector */
    public Vector getNormal(Point point);
}
