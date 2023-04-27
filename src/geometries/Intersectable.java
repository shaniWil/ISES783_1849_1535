package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
/**
 * Interface for finding intersections points
 */
public interface Intersectable {

     /** @param ray {@link Ray} point to object
      * @return List of intersections {@link Point}s */
     List<Point> findIntsersections(Ray ray);
}
