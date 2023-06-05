package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a light of Direction Light type
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class DirectionalLight extends Light implements LightSource{
    Vector direction;

    /**
     * setter direction at Builder Method pattern
     * @param direction
     * @return the Light
     */
    public DirectionalLight setDirection(Vector direction) {
        this.direction = direction;
        return this;
    }

    protected DirectionalLight(Color intensity, Vector directionIn) {
        super(intensity);
        direction = directionIn;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
