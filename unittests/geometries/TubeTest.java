package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 * @author raaya feldmar
 */
class TubeTest {

    Tube t1 = new Tube(1,new Ray(new Point(0,0,0),new Vector(1,0,0)));
    Vector v1=new Vector(0,1,0);
    Point p1= new Point(1,1,0);
    Point p2= new Point(0,1,0);
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d,t1.getNormal(p1).length(),0.00001d,"ERROR: getNormal() the normal is not normalized");
        //TC02: Test that the normal is orthogonal to the tube
        assertEquals(v1,t1.getNormal(p1),"ERROR: getNormal() the normal does not orthogonal to the tube");

        // =============== Boundary Values Tests ==================
        // TC10: Test the normal that is orthogonal to the beginning of the ray
        assertEquals(v1,t1.getNormal(p2),"ERROR: getNormal() the normal does not orthogonal to the tube");

    }
}