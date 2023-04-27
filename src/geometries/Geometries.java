package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

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
        for (Intersectable item : geometriesIn) {
            geometries.add(item);
        }
    }


    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> Intersection = new LinkedList<>();

        //go threw all the geometries and add their intersections
        for (Intersectable intersectable: geometries) {
            List<Point> currentIntersection = intersectable.findIntsersections(ray);
            if(currentIntersection != null) //no intersection was found
                Intersection.addAll(currentIntersection);
        }

        if(Intersection.size() == 0)
            return null;
        return Intersection;
    }
    /**if (geometries.size() == 0)
            return null;
        boolean flag = false;
        for (Intersectable item : geometries) {
            if (!item.findIntsersections(ray).isEmpty())
                flag = true;
            if (flag)
                break;
        }

        if (flag) {
            List<Point> IntsersectionPoints = new LinkedList<>();
            for (Intersectable item : geometries) {
               List<Point> toAdd = item.findIntsersections(ray);
                if (!toAdd.isEmpty())
                    IntsersectionPoints.addAll(toAdd);
            }
            return IntsersectionPoints;
        }
        return null;*/

}
