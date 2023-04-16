package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Objects;

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
       Vector v1= secondPoint.subtract(firstPoint);//(-1,1,0)
       Vector v2 = thirdPoint.subtract(firstPoint);//(-1,0,0)
       this.normal = v1.crossProduct(v2).normalize();
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
        return normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (!Objects.equals(q0, plane.q0)) return false;
        return Objects.equals(normal, plane.normal);
    }

    @Override
    public int hashCode() {
        int result = q0 != null ? q0.hashCode() : 0;
        result = 31 * result + (normal != null ? normal.hashCode() : 0);
        return result;
    }
}
