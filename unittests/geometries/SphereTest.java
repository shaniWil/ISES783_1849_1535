package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class SphereTest {

    Sphere sphere1 = new Sphere(2, new Point(-4, 0, 0));

    Sphere sphere2 = new Sphere(1, new Point(0, 0, 0));

    @Test
    public void testGetNormal() {
        Vector normal = sphere2.getNormal(new Point(0, 0, 1));
        Vector v1 = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d,
                normal.length(),
                0.00001d,
                "ERROR: getNormal() the normal is not normalized");

        //TC02: Test that the normal is orthogonal to the sphere
        assertEquals(v1,
                    normal,
                    "ERROR: getNormal() the normal does not orthogonal to the sphere");
    }

    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray starts inside the sphere (1 point)
        assertEquals(List.of(new Point(-3,0,1.7320508075688772)),
                sphere1.findIntsersections(new Ray((new Point(-3,0,0)),new Vector(0,0,1)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC02: Ray starts before and crosses the sphere (2 points)
        //assertEquals(List.of(new Point(-2,0,0),new Point(-4,0,-2)),
                //sphere1.findIntsersections(new Ray((new Point(0,0,2)),new Vector(-2,0,-2)))
                //,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC03: Ray starts after the sphere (0 points)
        assertNull(sphere1.findIntsersections(new Ray((new Point(0, 0, 2)), new Vector(2, 0, 2))), "ERROR: FindIntsersections() the intersection point is incorrect");
        // TC04: Ray's line is outside the sphere (0 points)
        assertNull(sphere1.findIntsersections(new Ray((new Point(2, 0, 0)), new Vector(0, -1, 0))), "ERROR: FindIntsersections() the intersection point is incorrect");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(-4,-2,0)),
                sphere1.findIntsersections(new Ray((new Point(-2,0,0)),new Vector(-2,-2,0)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere1.findIntsersections(new Ray((new Point(-2, 0, 0)), new Vector(1, 0, 0))), "ERROR: FindIntsersections() the intersection point is incorrect");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(List.of(new Point(-2,0,0),new Point(-6,0,0)),
                sphere1.findIntsersections(new Ray((new Point(-1,0,0)),new Vector(-1,0,0)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(-6,0,0)),
                sphere1.findIntsersections(new Ray((new Point(-2,0,0)),new Vector(-1,0,0)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point(-6,0,0)),
                sphere1.findIntsersections(new Ray((new Point(-3,0,0)),new Vector(-1,0,0)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point(-6,0,0)),
                sphere1.findIntsersections(new Ray((new Point(-4,0,0)),new Vector(-1,0,0)))
                ,"ERROR: FindIntsersections() the intersection point is incorrect");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere1.findIntsersections(new Ray((new Point(-2, 0, 0)), new Vector(1, 0, 0))), "ERROR: FindIntsersections() the intersection point is incorrect");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere1.findIntsersections(new Ray((new Point(-1, 0, 0)), new Vector(1, 0, 0))), "ERROR: FindIntsersections() the intersection point is incorrect");

    }
}