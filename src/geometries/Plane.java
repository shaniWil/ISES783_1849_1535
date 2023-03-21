package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private Point q0;
    private Vector normal;

    public Plane(Point firstPoint ,Point secondPoint,Point thirdPoint) {
       q0 = firstPoint;
       normal = null;
    }

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Vector getNormal() {
        return null;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }


}
