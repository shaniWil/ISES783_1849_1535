package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Geometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

/** Ray class represents ray in 3D Cartesian coordinate system
 *  @author Raaya Feldmar & Shani Wilamowsky */
public class Ray {
    private static final double DELTA = 0.1;
    private final Point p0;
    private final Vector dir;

    public static class RayColor {
        public Ray ray;
        public Color color;

        public RayColor(Ray ray, Color color) {
            this.ray = ray;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RayColor rayColor = (RayColor) o;

            if (!Objects.equals(ray, rayColor.ray)) return false;
            return Objects.equals(color, rayColor.color);
        }

        @Override
        public int hashCode() {
            int result = ray != null ? ray.hashCode() : 0;
            result = 31 * result + (color != null ? color.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "RayColor{" +
                    "ray=" + ray +
                    ", color=" + color +
                    '}';
        }
    }
    /**
     * Constructor to initialize Ray based object with its p0 and direction
     * @param p0 point of reference
     * @param dir vector perpendicular to the plane
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Ray(Point head, Vector direction, Vector normal){
        if(direction.dotProduct(normal)>0)
            this.p0= head.add(normal.scale(DELTA));
        else if(direction.dotProduct(normal)<0)
            this.p0= head.add(normal.scale(-DELTA));
        else this.p0 = head;
        this.dir = direction;
    }
    public Vector getDir()
    {
        return dir;
    }

    public Point getP0()
    {
        return p0;
    }

    public Point getPoint(double t) {
        return p0.add(dir.scale(t));
    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints){
        if (geoPoints == null || geoPoints.isEmpty())
            return null;

        GeoPoint closestPoint = geoPoints.get(0);
        Double closestDistance = closestPoint.point.distance(p0);

        for (GeoPoint tempGeoPoint : geoPoints) {

            if (tempGeoPoint.point.distance(p0) < closestDistance) {
                // to create an object?
                closestDistance = tempGeoPoint.point.distance(p0);
                closestPoint = tempGeoPoint;
            }
        }
        return closestPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray other)) return false;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
