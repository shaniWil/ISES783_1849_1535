package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * Interface for finding intersections points
 */
public abstract class Intersectable {
     public static class GeoPoint {
          public Geometry geometry;
          public Point point;

          public GeoPoint(Geometry geometry, Point point) {
               this.geometry = geometry;
               this.point = point;
          }

          @Override
          public boolean equals(Object o) {
               if (this == o) return true;
               if (o == null || getClass() != o.getClass()) return false;

               GeoPoint geoPoint = (GeoPoint) o;

               if (!Objects.equals(geometry, geoPoint.geometry)) return false;
               return Objects.equals(point, geoPoint.point);
          }

          @Override
          public int hashCode() {
               int result = geometry != null ? geometry.hashCode() : 0;
               result = 31 * result + (point != null ? point.hashCode() : 0);
               return result;
          }

          @Override
          public String toString() {
               return "GeoPoint{" +
                       "geometry=" + geometry +
                       ", point=" + point +
                       '}';
          }
     }

     /** @param ray {@link Ray} point to object
      * @return List of intersections {@link Point}s */
     public List<Point> findIntersections(Ray ray) {
          var geoList = findGeoIntersections(ray);
          return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
     }
     public List<GeoPoint> findGeoIntersections(Ray ray)
     {
          return findGeoIntersectionsHelper(ray);
     }
     protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
