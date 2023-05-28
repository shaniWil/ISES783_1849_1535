package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class PlaneTest {
    Point po1 = new Point(1, 0, 0);
    Point po2 = new Point(0, 1, 0);
    Point po3 = new Point(0, 0, 0);
    Point point4 = new Point(0, 0, 1);
    Vector v1 = new Vector(0, 0, 1);
    Vector v2 = new Vector(0, 0, -1);
    Plane pl1 = new Plane(po1, po2, po3);
    Plane pl2 = new Plane(po1, v1);
    Plane plane3 = new Plane(po1, po2, point4);


    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the constructor proper
        assertEquals(pl2,
                    pl1,
                "ERROR: constructor() the plane is incorrect");

        // =============== Boundary Values Tests ==================
        //TC10: Test that throws exception if the first point equals to the third point.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(po1, po2, new Point(1, 0, 0)),
                "ERROR: constructor() the first point equals to the third point");

        //TC10: Test that throws exception if all point on the same ray
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(po1, po2, new Point(-1, 2, 0)),
                "ERROR: constructor() all 3 point on the same ray");

    }

    @Test
    public void testGetNormal() {
        Vector normal = pl1.getNormal(po3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d, normal.length(), 0.00001, "ERROR: getNormal() the normal is not normalized");
        // TC02: Test that the normal is orthogonal to the plane
        assertTrue((normal.equals(v1)) || (normal.equals(v2)), "ERROR: getNormal() the normal does not orthogonal to the plane");
    }

    @Test
    void testFindIntsersections() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: The Ray neither orthogonal nor parallel to the plane and  intersects the plane.
        assertEquals(List.of(point4),
                plane3.findIntersections(new Ray((new Point(0,0,2)),v2))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC02: The Ray neither orthogonal nor parallel to the plane and do not intersect the plane.
        assertNull(plane3.findIntersections(new Ray((new Point(0, 0, 2)), v1)), "ERROR: FindIntsersections() the ray do not intersect the plane");
        // =============== Boundary Values Tests ==================
        //TC10: Ray is parallel to the plane and  included in the plane.
        assertNull(plane3.findIntersections(new Ray((new Point(1, 0, 0)),
                new Vector(new Double3(0, -1, 1)))), "ERROR: FindIntsersections() the ray is parallel to the plane and included");
        //TC11: Ray is parallel to the plane and not included in the plane.
        assertNull(plane3.findIntersections(new Ray((new Point(2, 0, 0)),
                new Vector(new Double3(0, -1, 1)))), "ERROR: FindIntsersections() the ray is parallel to the plane and not included");
        //TC12: Ray is orthogonal to the plane and 𝑃0 begins before the plane.
        //assertEquals(new Point(0.6,0.2,0.2),
                //plane3.findIntsersections(new Ray((new Point(0.5,0,0)),
                  //      new Vector(new Double3(1,1,1)))),
                //"ERROR: FindIntsersections() the ray is orthogonal to the plane and p0  begins before the plane");
        //TC13: Ray is orthogonal to the plane and 𝑃0 begins in the plane.
        assertNull(plane3.findIntersections(new Ray((po1),
                new Vector(new Double3(1, 1, 1)))), "ERROR: FindIntsersections() the ray is orthogonal to the plane p0 begins in the plane");
        //TC14: Ray is orthogonal to the plane and 𝑃0 begins after the plane.
        assertNull(plane3.findIntersections(new Ray((new Point(1.5, 0, 0)),
                new Vector(new Double3(1, 1, 1)))), "ERROR: FindIntsersections() the ray is orthogonal to the plane and p0 begins after the plane");
        //TC15: Ray is neither orthogonal nor parallel to and begins at the plane.
        assertNull(plane3.findIntersections(new Ray((point4), v2)), "ERROR: FindIntsersections() the ray is neither orthogonal nor parallel to and begins at the plane");
        //TC16: Ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane.
        assertNull(pl2.findIntersections(new Ray((po1), v2)), "ERROR: FindIntsersections() the ray is neither orthogonal nor parallel to and begins in " +
                "the same point which appears as reference point in the plane");
    }
}