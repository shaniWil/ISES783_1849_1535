package primitives;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/** Tests for the ray methods
 * @author Raaya Feldmar & Shani Wilamowsky */
class RayTest {
    Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
    Point point1 = new Point(2,0,0);
    Point point2 = new Point(2.5,0,0);
    Point point3 = new Point(3,0,0);
    List<Point> points = new LinkedList<>();



    @Test
    void findClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: The point is in the middle of the list and close to the beginning of the ray.
        points.add(point2);
        points.add(point1);
        points.add(point3);

        assertEquals(point1,
                ray.findClosestPoint(points),
        "findClosestPoint() , wrong point.") ;

        // =============== Boundary Values Tests ==================
        // TC10: The list is empty.
        points.clear();
        assertNull(ray.findClosestPoint(points),
                "findClosestPoint() , the list is empty.");

        // TC11: The point is at the beginning of the list .
        points.add(point1);
        points.add(point2);
        points.add(point3);

        assertEquals(point1,
                ray.findClosestPoint(points),
                "findClosestPoint() , the closest point is the first one.") ;

        // TC12: The point is at the end of the list .
        points.clear();
        points.add(point2);
        points.add(point3);
        points.add(point1);

        assertEquals(point1,
                ray.findClosestPoint(points),
                "findClosestPoint() , the closest point is the last one.") ;
    }
}