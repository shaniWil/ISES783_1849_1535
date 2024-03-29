package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class TriangleTest {
    Point po1 = new Point(1, 0, 0);
    Point po2 = new Point(0, 1, 0);
    Point po3 = new Point(0, 0, 0);
    Point point4 = new Point(-4, 0, 0);
    Point point5 = new Point(0, 0, 4);
    Point point6 = new Point(4, 0, 0);
    Vector v1 = new Vector(0, 0, 1);
    Vector v2 = new Vector(0, 0, -1);
    Triangle triangle1 = new Triangle(po1, po2, po3);
    Triangle triangle2 = new Triangle(point4, point5, point6);


    @Test
    public void testGetNormal() {
        Vector normal = triangle1.getNormal(po3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d,
                normal.length(),
                0.00001,
                "ERROR: getNormal() the normal is not normalized");

        // TC02: Test that the normal is orthogonal to the triangle
        assertTrue((normal.equals(v1)) || (normal.equals(v2)),
                "ERROR: getNormal() the normal does not orthogonal to the triangle");

    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test intersections of ray with triangle in a regular case.
        assertEquals(List.of(new Point(0,0,2)),
                triangle2.findIntersections(new Ray((new Point(0,2,0)),new Vector(0,-1,1)))
                ,"ERROR: FindIntersection() the intersection point is incorrect");

        assertNull(triangle2.findIntersections(new Ray((new Point(0, 0, 1)), new Vector(0, 1, -1))), "ERROR: FindIntersection() the intersection point is incorrect");

        assertNull(triangle2.findIntersections(new Ray((new Point(0, 0, 3)), new Vector(0, 5, -3))), "ERROR: FindIntersection() the intersection point is incorrect");

        // =============== Boundary Values Tests ==================
        assertNull(triangle2.findIntersections(new Ray((new Point(0, 0, 2)), new Vector(2, 2, -2))), "ERROR: FindIntersection() the intersection point is incorrect");
        // על קוקוד
        assertNull(triangle2.findIntersections(new Ray((new Point(0, 0, 3)), new Vector(-4, 0, -3))), "ERROR: FindIntersection() the intersection point is incorrect");

        assertNull(triangle2.findIntersections(new Ray((new Point(0, 0, 2)), new Vector(5, 0, -2))), "ERROR: FindIntersection() the intersection point is incorrect");
    }
}