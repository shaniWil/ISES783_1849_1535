package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/** This class represents a plane, will serve the flat bodies
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Plane extends Geometry {
    private final Point q0;
    private final Vector normal;

    /** Constructor to initialize Plane based object by three points
     * @param firstPoint the coordinates of the first point
     * @param secondPoint the coordinates of the second point
     * @param thirdPoint the coordinates of the third point */
    public Plane(Point firstPoint ,Point secondPoint,Point thirdPoint) {
       q0 = firstPoint;
       Vector v1= firstPoint.subtract(secondPoint);//(-1,1,0)
       Vector v2 = firstPoint.subtract(thirdPoint);//(-1,0,0)
       normal = v1.crossProduct(v2).normalize();
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

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector rayDirection = ray.getDir();
        Point rayP0 = ray.getP0();

        if(rayP0.equals(q0))
            return null;
        double nv = alignZero(normal.dotProduct(rayDirection));
        if (isZero(nv))
            return null;
        double t = alignZero(alignZero(normal.dotProduct(q0.subtract(rayP0)) / nv));
        if (t > 0)
            return List.of(new GeoPoint(this, rayP0.add((rayDirection).scale(t))));
        return null;
    }
}
