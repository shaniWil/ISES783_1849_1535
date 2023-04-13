package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Point po1=new Point(1,0,0);
    Point po2=new Point(0,1,0);
    Point po3=new Point(0,0,0);
    Vector v1=new Vector(0,0,1);
    Vector v2=new Vector(0,0,-1);
    Plane pl1= new Plane(po1,po2,po3);
    Plane pl2=new Plane(po1,v1);

    @Test
    public void testConstructor() {
        assertEquals(pl2,pl1,"ERROR: constructor() the plane is incorrect");
        assertThrows(IllegalArgumentException.class, ()->new Plane(po1,po2,new Point(1,0,0)),"ERROR: constructor() all 3 point on the same ray");
        assertThrows(IllegalArgumentException.class, ()->new Plane(po1,po2,new Point(-1,2,0)),"ERROR: constructor() all 3 point on the same ray");

    }
    @Test
    public void testGetNormal() {
        Vector normal = pl1.getNormal(po3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal's length equals to 1.
        assertEquals(1d, normal.length(),0.00001, "ERROR: getNormal() the normal is not normalized");
        // TC02: Test that the normal is orthogonal to the plane
        assertTrue((normal.equals(v1))||(normal.equals(v2)),"ERROR: getNormal() the normal does not orthogonal to the plane");


    }
}