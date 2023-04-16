package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    // Empty constructor.
    public Geometries() {
        this.geometries = new LinkedList<>();
    }
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
