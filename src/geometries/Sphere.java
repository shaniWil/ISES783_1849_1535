package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry implements Geometry {
    Point center;

    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public Vector getNormal(Point point) {
        return null;
    }
}
