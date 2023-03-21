package geometries;

import primitives.Point;
import primitives.Vector;

/** This class represents a plane, will serve the flat bodies
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Plane implements Geometry {
    private final Point q0;
    private final Vector normal;

    /** Constructor to initialize Plane based object by three points
     * @param firstPoint the coordinates of the first point
     * @param secondPoint the coordinates of the second point
     * @param thirdPoint the coordinates of the third point */
    public Plane(Point firstPoint ,Point secondPoint,Point thirdPoint) {
       this.q0 = firstPoint;
       this.normal = null;
    }

    /** Constructor to initialize Plane based object with a point in space and a normal to the plane
     * @param q0 point in space
     * @param normal normal to the plane */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /** returns the normal vector to the plane
     * @return     the normal */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }


}
