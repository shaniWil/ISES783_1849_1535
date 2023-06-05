package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a light of Spotlight type
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class SpotLight extends PointLight{
    Vector direction;

    /**
     * constructor of SpotLight
     * @param intensity the intensity of the light (color)
     * @param position the location of the source light
     * @param direction attenuation coefficient
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * setter direction at Builder Method pattern
     * @param direction
     * @return the Light
     */
    public SpotLight setDirection(Vector direction) {
        this.direction = direction.normalize();
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point).normalize())));
    }

}
