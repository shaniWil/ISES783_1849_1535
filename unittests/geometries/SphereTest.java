package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class SphereTest {

    Sphere s1 = new Sphere(1, new Point(0, 0, 0));

    @Test
    public void testGetNormal() {
        Vector normal = s1.getNormal(new Point(0, 0, 1));
        Vector v1 = new Vector(0, 0, 1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d, normal.length(), 0.00001d, "ERROR: getNormal() the normal is not normalized");
        //TC02: Test that the normal is orthogonal to the sphere
        assertEquals(v1, normal, "ERROR: getNormal() the normal does not orthogonal to the sphere");
    }
}