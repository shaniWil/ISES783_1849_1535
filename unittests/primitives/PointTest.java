package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for primitives.Point class
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */

class PointTest {

    Point p1 = new Point(1, 1, 1);

    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that when subtracting two points, a correct result vector is obtained.
        assertEquals(new Vector(0, -1, -2),
                p1.subtract(new Point(1, 2, 3)),
                "subtract()  wrong result vector");

        // =============== Boundary Values Tests ==================
        // TC10: Test that when a point is subtracted from itself, an exception of the zero vector is indeed thrown.
        assertThrows(IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "Vector zero is illegal");
    }

    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test addition between two points, there is no boundary cases.
        assertEquals(new Point(2, 3, 4),
                p1.add(new Vector(1, 2, 3)),
                "add()  wrong result vector");
    }

    @Test
    public void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test distance squared between two points.
        assertEquals(5d,
                p1.distanceSquared(new Point(new Double3(1, 2, 3))),
                "distanceSquared()  wrong result");

        // =============== Boundary Values Tests ==================
        // TC10: Test that distance squared from a point to herself is indeed 0.
        assertEquals(0d,
                p1.distanceSquared(new Point(new Double3(1, 1, 1))),
                "distanceSquared()  wrong result");
    }

    @Test
    public void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test distance between two points.
        assertEquals(2d,
                p1.distance(new Point(new Double3(1, 1, 3))),
                "distance()  wrong result");

        // =============== Boundary Values Tests ==================
        // TC10: Test that distance from a point to herself is indeed 0.
        assertEquals(0d,
                p1.distance(new Point(new Double3(1, 1, 1))),
                "distance()  wrong result");
    }

}