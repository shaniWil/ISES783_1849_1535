package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private Point position;
    double Kc = 1;
    double Kl =  0;
    double Kq = 0;


    protected PointLight(Color intensity, Point positionIn) {
        super(intensity);
        position = positionIn;
    }

    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        Kq = kq;
        return this;
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double distance = position.distance(point);
        return getIntensity().reduce((Kc + Kl*distance + Kq* distance*distance));
    }
}
