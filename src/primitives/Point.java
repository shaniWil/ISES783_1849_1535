package primitives;

import java.util.Objects;

/**
 * Ray class represents ray in 3D Cartesian coordinate system
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class Point {

    public static final Point ZERO = new Point(0,0,0);
    /**
     * the value of the point
     */
    protected final Double3 xyz;

    /**
     * Constructor to initialize Ray based object with value of x,y,z on cartesian coordinate system
     *
     * @param x the value of the x-axis
     * @param y the value of the y-axis
     * @param z the value of the z-axis
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Ray based object with value of Double3 on cartesian coordinate system
     *
     * @param xyz the value of the x,y and z-axis
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Subtracting between 2 points to get new vector from the second point to first point
     *
     * @param point operand for subtraction
     * @return result vector of subtraction
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Addition a vector to point to get new point
     *
     * @param vector operand for addition
     * @return result point of addition
     */
    public Point add(Vector vector) {
        return new Point(vector.xyz.add(xyz));
    }

    /**
     * The distance squared between 2 points.
     *
     * @param secondDouble3 second point operand for the distance
     * @return the distance squared
     */
    public double distanceSquared(Point secondDouble3) {
        return (xyz.d1 - secondDouble3.xyz.d1) * (xyz.d1 - secondDouble3.xyz.d1) +
                (xyz.d2 - secondDouble3.xyz.d2) * (xyz.d2 - secondDouble3.xyz.d2) +
                (xyz.d3 - secondDouble3.xyz.d3) * (xyz.d3 - secondDouble3.xyz.d3);
    }

    /**
     * The distance between 2 points.
     *
     * @param secondDouble3 second point operand for the distance
     * @return the distance
     */
    public double distance(Point secondDouble3) {
        return Math.sqrt(distanceSquared(secondDouble3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
}


