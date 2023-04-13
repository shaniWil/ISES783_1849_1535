package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for primitives.Point class
 * @author Yossi Cohen
 */

class PointTest {

    Point  p1= new Point(1,1,1);
    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:
        assertEquals(new Vector(0, -1, -2),p1.subtract(new Point(1,2,3)), "subtract()  wrong result vector");
        // =============== Boundary Values Tests ==================
        // TC10:
        assertThrows(IllegalArgumentException.class, ()->p1.subtract(p1), "Vector zero is illegal");
    }

    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:
        assertEquals(new Point(2,3,4),p1.add(new Vector(1,2,3)), "add()  wrong result vector");
    }

    @Test
    public void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:
        assertEquals(5d,p1.distanceSquared(new Double3(1,2,3)), "distanceSquared()  wrong result");
        // =============== Boundary Values Tests ==================
        // TC10:
        assertEquals(0d, p1.distanceSquared(new Double3(1,1,1)), "distanceSquared()  wrong result");
    }

    @Test
    public void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:
        assertEquals(2d,p1.distance(new Double3(1,1,3)), "distance()  wrong result");
        // =============== Boundary Values Tests ==================
        // TC10:
        assertEquals(0d, p1.distance(new Double3(1,1,1)), "distance()  wrong result");
    }

}