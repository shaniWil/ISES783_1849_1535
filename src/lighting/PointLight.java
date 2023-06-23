package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a light of Point Light type
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    double Kc = 1;
    double Kl =  0;
    double Kq = 0;


    public PointLight(Color intensity, Point positionIn) {
        super(intensity);
        position = positionIn;
    }

    /**
     * setter position at Builder Method pattern
     * @param position
     * @return the Light
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * setter kc at Builder Method pattern
     * @param kc
     * @return the Light
     */
    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }

    /**
     * setter kl at Builder Method pattern
     * @param kl
     * @return the Light
     */
    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    /**
     * setter kq at Builder Method pattern
     * @param kq
     * @return the Light
     */
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

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
