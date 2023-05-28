package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntsersections() {
        Plane plane = new Plane(new Point(1,0,0), new Vector(0,1,0));
        Triangle triangle = new Triangle(new Point(4,0,0),new Point(-4,0,0),new Point(0,0,-2));
        Sphere sphere =new Sphere (2,new Point(0,0,0));

        Geometries geometries = new Geometries(plane,triangle,sphere);


        // ============ Equivalence Partitions Tests ==============
        // TC01: Some geometries, but not all of them intersect.
        Ray ray = new Ray(new Point(0,-4,0), new Vector(0,4,1));

        assertEquals(3,
                geometries.findIntersections(ray).size(),
                "ERROR: FindIntsersections() the amount of intersection points is incorrect"  );

        // =============== Boundary Values Tests ==================
        // TC11: The ray intersects all the geometriesc
        ray = new Ray(new Point(0,-4,0), new Vector(0,1,-1));
        assertEquals(1,
                geometries.findIntersections(ray).size(),
                "ERROR: FindIntsersections() Only one of the geometries intersects" );
        // TC12: The ray intersects one of the geometries
        ray = new Ray(new Point(0,-4,0), new Vector(0,4,4));
        assertEquals(1,
                geometries.findIntersections(ray).size(),
                "ERROR: FindIntsersections() Only one of the geometries intersects" );
        
        // TC13: The ray does not intersect any geometries (0 points)
        ray = new Ray(new Point(0,-4,0), new Vector(0,0,1));
        assertNull(geometries.findIntersections(ray),
                "ERROR: FindIntsersections() The ray does not intersect any geometries ,found an intersection point" );

        // TC14: Empty collection of geometries
        geometries = new Geometries();
        assertNull(geometries.findIntersections(ray),
                "ERROR: FindIntsersections() Empty collection of geometries, no intersections" );


    }
}