package primitives;

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
