package primitives;

import java.util.List;
import java.util.Objects;

/** Ray class represents ray in 3D Cartesian coordinate system
 *  @author Raaya Feldmar & Shani Wilamowsky */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray based object with its p0 and direction
     * @param p0 point of reference
     * @param dir vector perpendicular to the plane
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
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
        if (points.isEmpty())
            return null;

        Point closestPoint = points.get(0);
        Double closestDistance = closestPoint.distance(p0.xyz);

        for (Point tempPoint : points) {

            if (tempPoint.distance(p0.xyz) < closestDistance) {
                // to create an object?
                closestDistance = tempPoint.distance(p0.xyz);
                closestPoint = tempPoint;
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
