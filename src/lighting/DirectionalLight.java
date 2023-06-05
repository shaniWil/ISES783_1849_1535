package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    Vector direction;

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
