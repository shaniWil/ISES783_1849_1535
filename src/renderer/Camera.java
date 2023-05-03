package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/** A class for representing the camera
 * @author Raaya Feldmar & Shani Wilamowsky */
public class Camera {
    private final Point location;
    private final Vector vUp;
    private final Vector vRight;
    private final Vector vTo;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    public Camera(Point location, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("vUp and vTO are not orthogonal");
        this.location = location;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = (vUp.crossProduct(vTo)).normalize();

    }

    // getters & setters.
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

    public Camera setVPSize(double widthIn, double heightIn) {
        if (isZero(widthIn) || isZero(heightIn))
            throw new IllegalArgumentException("the width or the height is zero");
        width = widthIn;
        height = heightIn;
        return this;
    }

    public Camera setVPDistance(double distanceIn) {
        if (isZero(distanceIn))
            throw new IllegalArgumentException("the distance is zero");
        distance = distanceIn;
        return this;
    }

    public Camera setRayTracerBase(RayTracerBase rayTracerBaseIn) {
        this.rayTracerBase = rayTracerBaseIn;
        return this;
    }

    public Camera setImageWriter(ImageWriter ImageWriterIn) {
        this.imageWriter = ImageWriterIn;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracerBaseIn) {
        this.rayTracerBase = rayTracerBaseIn;
        return this;
    }
    
    /**
     * Constructs a ray from the camera through pixel i,j.
     * @param nX number of pixels on the width of the view plane.
     * @param nY number of pixels on the height of the view plane.
     * @param j location of the pixel in the X direction.
     * @param i location of the pixel in the Y direction.
     * @return the constructed ray - from p0 through the wanted pixel.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pIJ = location.add(vTo.scale(distance));
        double rY = alignZero(height / nY);
        double rX = alignZero(width / nX);
        double yI = alignZero((i - (nY - 1.0) / 2.0) * ((-1) * rY));
        double xJ = alignZero((j - (nX - 1.0) / 2.0) * ((-1) * rX));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        return new Ray(location, pIJ.subtract(location));
    }

    private Color castRay(int j, int i) {
        return rayTracerBase.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i));
    }
    
    /**
     * render the image and fill the pixels with the desired colors
     * using the ray tracer to find the colors
     * and the image writer to color the pixels
     * @throws MissingResourceException if one of the fields are uninitialized
     */
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("All of the filed should be initialized",
                    "Camera",
                    "imageWriter");
        if (rayTracerBase == null)
            throw new MissingResourceException("All of the filed should be initialized",
                    "Camera",
                    "rayTracerBase");

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(j, i, castRay(j, i));
            }

        }
    }

    /**
    * Print a grid on the image without running over the original image
    * @param interval the size of the grid squares
    * @param color the color of the grid
    * @throws MissingResourceException
    */
    public void printGrid(int interval, Color color) {
        renderImage();
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++)
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
        }
    }

    /**
     * Create the image file using the image writer
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("All of the filed should be initialized",
                    "Camera",
                    "imageWriter");
        imageWriter.writeToImage();
    }

}
