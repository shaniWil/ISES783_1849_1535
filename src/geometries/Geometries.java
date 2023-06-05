package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** This class represents all the complex geometries body
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    /** Empty constructor.*/
    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    /**
     * constructor of complex geometric that get how many geometries.
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {

        this.geometries = new LinkedList<>();

        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * adding geometries to geometric
     * @param geometries
     */
    public void add(Intersectable... geometries)
    {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> geoIntersection = new LinkedList<>();

        //go threw all the geometries and add their intersections
        for (Intersectable intersectable: geometries) {
            List<GeoPoint> currentIntersection = intersectable.findGeoIntersections(ray);
            if(currentIntersection != null) //no intersection was found
                geoIntersection.addAll(currentIntersection);
        }

        if(geoIntersection.size() == 0)
            return null;
        return geoIntersection;
    }

}
