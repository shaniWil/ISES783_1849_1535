package primitives;

import java.util.Objects;

public class Point {
     final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Vector subtract(Point point) {
        Vector vector = new Vector(xyz.subtract(point.xyz));
        return vector;
    }

    public Point add(Vector vector) {
       Point addP = new Point(vector.xyz.add(xyz));
        return addP;
    }

    public double distanceSquared(Double3 secondDouble3) {
        double distance = (xyz.d1 - secondDouble3.d1) * (xyz.d1 - secondDouble3.d1) +
                (xyz.d2 - secondDouble3.d2) * (xyz.d2 - secondDouble3.d2) +
                (xyz.d3 - secondDouble3.d3) * (xyz.d3 - secondDouble3.d3);
        return distance;
    }

    public double distance(Double3 secondDouble3) {
        return Math.sqrt(distanceSquared(secondDouble3)) ;
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
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}


