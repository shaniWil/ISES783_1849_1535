package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/** This interface will serve all geometric bodies.
 * @author Raaya Feldmar & Shani Wilamowsky */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /** Receives a point on the surface of the geometric body
     * and returns the normal vector to the body at this point.
     * @param point on the surface
     * @return     the vertical vector */
    public abstract Vector getNormal(Point point);
}
