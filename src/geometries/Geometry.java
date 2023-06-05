package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * This interface will serve all geometric bodies.
 *
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * @return the geometry's emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @return the geometry's material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter emission at Builder Method pattern
     * @param emission
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * setter material at Builder Method pattern
     * @param material
     * @return the geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Receives a point on the surface of the geometric body
     * and returns the normal vector to the body at this point.
     *
     * @param point on the surface
     * @return the vertical vector
     */
    public abstract Vector getNormal(Point point);
}
