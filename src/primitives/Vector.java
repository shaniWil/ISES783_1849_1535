package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("A zero vector");
    }

    public Vector(Double3 double3) {
        super(double3);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("A zero vector");
    }

    public Vector add(Vector vector) {
        Vector addVector = new Vector(this.xyz.add(vector.xyz));
        return addVector;
    }

    public Vector scale(double scalar) {
        Vector scaledVector = new Vector(this.xyz.scale(scalar));
        return scaledVector;
    }

    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
    }

    public Vector crossProduct(Vector vector) {
        Vector crossVector = new Vector(this.xyz.d2 * vector.xyz.d3-this.xyz.d3 * vector.xyz.d2,
                this.xyz.d3 * vector.xyz.d1-this.xyz.d1 * vector.xyz.d3,
                this.xyz.d1 * vector.xyz.d2-this.xyz.d2 * vector.xyz.d1);
        return crossVector;
    }
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    public double length() {
        return Math.sqrt(this.dotProduct(this));
    }

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
