package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for Cylinder class
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder}.
     */

    Cylinder c1 = new Cylinder(1d, new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 5d);
    Vector v1 = new Vector(0, 1, 0);
    Vector v2 = new Vector(1, 0, 0);
    Point p1 = new Point(1, 1, 0);
    Point p2 = new Point(5, 0.5, 0);
    Point p3 = new Point(0, 0.5, 0);
    Point p4 = new Point(0, 0, 0);
    Point p5 = new Point(5, 0, 0);

    @Test
    public void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d,
                c1.getNormal(p1).length(),
                0.00001d,
                "ERROR: getNormal() the normal is not normalized");

        //TC02: Test that the normal is orthogonal to the Cylinder
        assertEquals(v1,
                c1.getNormal(p1),
                "ERROR: getNormal() the normal does not orthogonal to the Cylinder");

        //TC03: Test the normal for point on the top
        assertEquals(v2,
                c1.getNormal(p2),
                "ERROR: getNormal() the normal does not orthogonal to the Cylinder");

        //TC04: Test the normal for point on the bottom
        assertEquals(v2.scale(-1),
                c1.getNormal(p3),
                "ERROR: getNormal() the normal does not orthogonal to the Cylinder");

        // =============== Boundary Values Tests ==================
        // TC10: Test the normal of the bottom base center
        assertEquals(v2.scale(-1),
                c1.getNormal(p4),
                "ERROR: getNormal() the normal does not orthogonal to the Cylinder's bottom base center");

        //TC04: Test the normal of the top base center
        assertEquals(v2,
                c1.getNormal(p5),
                "ERROR: getNormal() the normal does not orthogonal to the Cylinder's top base center");
    }
}