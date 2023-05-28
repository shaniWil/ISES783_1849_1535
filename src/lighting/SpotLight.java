package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


public class SpotLight extends PointLight{
    Vector direction;

    protected SpotLight(Color intensity, Point positionIn, Vector directionIn) {
        super(intensity, positionIn);
        direction = directionIn.normalize();
    }

    public SpotLight setDirection(Vector direction) {
        this.direction = direction.normalize();
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point).normalize())));
    }
}
