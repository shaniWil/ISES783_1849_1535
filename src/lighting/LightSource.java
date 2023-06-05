package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/** This interface will serve all lights source.
 * @author Raaya Feldmar & Shani Wilamowsky */
public interface LightSource {
    /**
     * find the intensity that come to point from the light source
     * @param point
     * @return the intensity of the source light
     */
    public Color getIntensity(Point point);

    /**
     * find the direction of the source light to the point
     * @param point
     * @return direction
     */
    public Vector getL(Point point);

    double getDistance(Point point);
}
