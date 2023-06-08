package lighting;

import static org.junit.jupiter.api.Assertions.*;
import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

public class pictureTest {
    private Scene scene = new Scene("Test scene");

    /** Produce a picture of a sphere lighted by a spotlight */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(5d, new Point(0, -20, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(2.5d, new Point(0, -20, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(5d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(2.5d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(5d, new Point(0, 20, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(2.5d, new Point(0, 20, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("the picture", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}