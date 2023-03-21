
package geometries;

import primitives.Point;

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
}
