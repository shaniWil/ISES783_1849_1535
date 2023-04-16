package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Raaya Feldmar & Shani Wilamowsky
 */
class TriangleTest {
    Point po1=new Point(1,0,0);
    Point po2=new Point(0,1,0);
    Point po3=new Point(0,0,0);
    Vector v1=new Vector(0,0,1);
    Vector v2=new Vector(0,0,-1);
    Triangle tr1= new Triangle(po1,po2,po3);

    @Test
    public void testGetNormal() {
        Vector normal = tr1.getNormal(po3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d, normal.length(),0.00001, "ERROR: getNormal() the normal is not normalized");
        // TC02: Test that the normal is orthogonal to the triangle
        assertTrue((normal.equals(v1))||(normal.equals(v2)),"ERROR: getNormal() the normal does not orthogonal to the triangle");


    }

}