package primitives;

/**Vector class represents vector in 3D Cartesian coordinate system
 */
public class Vector extends Point {
    /**Constructor to initialize Vector based object with three number values of its point
     *@param x first number value of the point
     *@param y second number value of the point
     *@param z third number value of the point */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("A zero vector");
    }

    /**Constructor to initialize Vector based object with double3 that represents its point
     *@param double3 the three value of the point */
    public Vector(Double3 double3) {
        super(double3);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("A zero vector");
    }

    /**
     * addition vectors for result vector
     * @param vector operand for add
     * @return vector of result of add
     */
    public Vector add(Vector vector) {
        return new Vector(this.xyz.add(vector.xyz));
    }

    /**
     * scale vector with scalar
     * @param scalar number operand for scaling
     * @return result vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * dot product between 2 vectors
     * @param vector operand for scaling
     * @return result scalar
     */
    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
    }

    /**
     * cross product between 2 vectors
     * @param vector operand for scaling
     * @return result vector
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(this.xyz.d2 * vector.xyz.d3-this.xyz.d3 * vector.xyz.d2,
                this.xyz.d3 * vector.xyz.d1-this.xyz.d1 * vector.xyz.d3,
                this.xyz.d1 * vector.xyz.d2-this.xyz.d2 * vector.xyz.d1);
    }

    /**
     * the squared length of the vector
     * @return squared length
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * the length of the vector
     * @return length
     */
    public double length() {
        return Math.sqrt(this.dotProduct(this));
    }

    /**
     * normalize the vector to the unit vector
     * @return the unit vector
     */
    public Vector normalize() {
       return this.scale(1/this.length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return super.equals(other);
        return false;
    }

    @Override
    public int hashCode() { return xyz.hashCode(); }

    @Override
    public String toString() { return "‐>" + super.toString(); }

}
