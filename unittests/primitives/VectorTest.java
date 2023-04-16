package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Raaya Feldmar & Shani Wilamowsky
 */


class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-2, -4, -6);
    Vector v4 = new Vector(1, 2, 2);

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that Vector zero throws an exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: Vector zero is illegal");
    }

    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that addition of 2 vectors succeeded
        assertEquals(new Vector(1, 5, 1), v1.add(v2), "The vector addition failed");

        // =============== Boundary Values Tests ==================
        // TC10: Test Vector + -itself equals to zero
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)), "ERROR: Vector + -itself does not throw an exception");

    }

    @Test
    public void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that multiplication of vector by scalar succeeded
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "The Vector multiplication by scalar failed");

        // =============== Boundary Values Tests ==================
        // TC10: Test that multiplication of vector by zero throws an exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "ERROR: Vector zero is illegal");


    }

    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        double d1 = v1.dotProduct(v2);
        double d2 = v1.dotProduct(v3);
        // TC01: Test that length of dot-product is proper
        assertEquals(-28d, d2, 0.00001, "dotProduct() wrong result length");

        // =============== Boundary Values Tests ==================
        //TC10: Test that length of dot-product of orthogonal vectors is 0.
        assertEquals(0d, d1, 0.00001, "dotProduct() wrong result length");

        // TC10: Test dot-product result orthogonality to its operands
        assertFalse(isZero(v1.dotProduct(new Vector(1, 3, -2))), "dotProduct() result is not orthogonal to 1st operand");
        assertFalse(isZero(v1.dotProduct(new Vector(0, 2, -2))), "dotProduct() result is not orthogonal to 2nd operand");
        assertFalse(isZero(v1.dotProduct(new Vector(0, 3, 0))), "dotProduct() result is not orthogonal to 3rd operand");
    }

    @Test
    public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that cross product of 2 vectors succeeded
        assertEquals(new Vector(-13, 2, 3), v1.crossProduct(v2), "ERROR: crossProduct() wrong result");

        //=============== Boundary Values Tests ==================
        //TC10: Test cross product for parallel vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(new Vector(2, 4, 6)), "ERROR: crossProduct() for parallel vectors");
        //TC11: Test cross product by length
        assertEquals(v1.length() * v2.length(), v1.crossProduct(v2).length(), 0.00001, "ERROR: crossProduct() wrong result length");
        //TC12: Test that the result Vector orthogonal to 2 original vectors
        assertEquals(0d, v1.crossProduct(v2).dotProduct(v1), "ERROR: crossProduct() result is not orthogonal to its 1st operand");
        assertEquals(0d, v1.crossProduct(v2).dotProduct(v2), "ERROR: crossProduct() result is not orthogonal to its 2nd operand");
    }

    @Test
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the length squared is correct
        assertEquals(
                14d,
                v1.lengthSquared(),
                0.00001d,
                "ERROR: lengthSquared() result is incorrect"
        );
    }

    @Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the length is correct
        assertEquals(3d, v4.length(), 0.00001d, "ERROR: length() result is incorrect");
    }

    @Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the length after normalize is 1.
        assertEquals(1d, v1.normalize().length(), 0.00001d, "ERROR: normalize() the Vector length should be equal to 1");
    }
}