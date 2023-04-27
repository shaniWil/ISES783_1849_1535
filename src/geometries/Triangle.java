
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** This class represents a geometric body of triangle type
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Triangle extends Polygon {

    /** Constructor to initialize Triangle based object by three points
     * @param firstPoint the coordinates of the first point
     * @param secondPoint the coordinates of the second point
     * @param thirdPoint the coordinates of the third point */
    public Triangle(Point firstPoint ,Point secondPoint,Point thirdPoint) {
        super(firstPoint,secondPoint,thirdPoint);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector rayDir = ray.getDir();
        Vector vector1 = vertices.get(0).subtract(p0);
        Vector vector2 = vertices.get(1).subtract(p0);
        Vector vector3 = vertices.get(2).subtract(p0);

        Vector n1 = vector1.crossProduct(vector2);
        Vector n2 = vector2.crossProduct(vector3);
        Vector n3 = vector3.crossProduct(vector1);

        double dot1 = rayDir.dotProduct(n1);
        double dot2 = rayDir.dotProduct(n2);
        double dot3 = rayDir.dotProduct(n3);

        if (dot1 > 0 && dot2 > 0 && dot3 > 0)
            return plane.findIntsersections(ray);

        if (dot1 < 0 && dot2 < 0 && dot3 < 0)
            return plane.findIntsersections(ray);
        return null;
    }
}
