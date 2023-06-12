package lighting;

import static org.junit.jupiter.api.Assertions.*;
import static java.awt.Color.*;

import geometries.Plane;
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

    /** Produce a picture with all the effects */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(80);

        scene.geometries.add( //
                new Sphere(10d, new Point(-5, -20, -70)).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKr(1)),
                new Sphere(10d, new Point(15, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.6)),
                new Sphere(5d, new Point(15, 0, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(3d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.65)),
                new Sphere(2d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75)),
                new Sphere(1d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.85)),
                new Sphere(0.5d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.95)),
                new Plane(new Point(0,0, 0), new Vector(0,0,-1)).setMaterial(new Material().setKd(0.2)).setEmission(new Color(RED)),
                new Plane(new Point(0,0, -100), new Vector(0,0,1)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(BLACK)),
                new Plane(new Point(-50,0, 0), new Vector(1,0,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(yellow)),
                new Plane(new Point(50,0, 0), new Vector(-1,0,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(35,191,3)),
                new Plane(new Point(0,50, 0), new Vector(0,-1,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(gray)),
                new Plane(new Point(0,-50, 0), new Vector(0,1,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(gray)));
        scene.lights.add( //
                new PointLight(new Color(1000, 900, 400), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        //scene.lights.add(new DirectionalLight(new Color(100, 100, 30), new Vector(-1, -1, -2)));

        camera.setImageWriter(new ImageWriter("the picture", 4000, 4000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}