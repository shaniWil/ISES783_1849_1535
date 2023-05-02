package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {
    private Point location;
    private Vector vUp;
    private Vector vRight;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;

    public Camera(Point location, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("vUp and vTO are not orthogonal");
        this.location = location;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = (vTo.crossProduct(vUp)).normalize();

    }
    public Point getLocation() {
        return location;
    }

    public Vector getvUp() {
        return vUp;
    }


    public Vector getvRight() {
        return vRight;
    }

    public Vector getvTo() {
        return vTo;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public Camera setVPSize(double widthIn, double heightIn)
    {
        if(isZero(widthIn) || isZero(heightIn))
            throw new IllegalArgumentException("the width or the height is zero");
        width = widthIn;
        height = heightIn;
        return this;
    }

    public Camera setVPDistance(double distanceIn)
    {
        if(isZero(distanceIn))
            throw new IllegalArgumentException("the distance is zero");
        distance = distanceIn;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i)
    {
        Point pIJ = location.add(vTo.scale(distance));
        double rY = alignZero(height / nY);
        double rX = alignZero(width / nX);
        double yI = alignZero((i- (nY - 1)/2) *( (-1) * rY));
        double xJ = alignZero((j- (nX - 1)/2) *( (-1) * rX));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        return new Ray(location, pIJ.subtract(location));
    }
}
