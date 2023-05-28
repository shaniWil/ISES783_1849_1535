package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    // Empty constructor.
    public Geometries() {
        this.geometries = new LinkedList<>();
    }
    public Geometries(Intersectable... geometriesIn) {

        this.geometries = new LinkedList<>();

        geometries.addAll(Arrays.asList(geometriesIn));
    }

    public void add(Intersectable... geometriesIn)
    {
        Collections.addAll(geometries, geometriesIn);
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
