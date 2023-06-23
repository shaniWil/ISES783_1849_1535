package renderer;

import static java.awt.Color.*;

import geometries.*;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;
import scene.Scene;

public class GeneralTest {
    private final Scene scene = new Scene("Test scene");
    Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150).setVPDistance(80);
    Camera camera1 = new Camera(new Point(-50, 0, -50), new Point(0,0,-50))//
            .setVPSize(150, 150).setVPDistance(80);
    Camera camera2= camera.scaleDegreesXY(20).setVPSize(150, 150).setVPDistance(80);
    Camera camera3= camera.scaleDegreesXY(180).setVPSize(150, 150).setVPDistance(80);
    private final Point point1= new Point(10,10,-20);
    private final Point point2= new Point(-10,10,-20);
    private final Point point3= new Point(10,2.5,-20);
    private final Point point4= new Point(2.5,2.5,-20);
    private final Point point5= new Point(-10,2.5,-20);
    private final Point point6= new Point(10,-10,-20);
    private final Point point7= new Point(2.5,-10,-20);
    private final Geometry triangle1 = new Triangle(point2,point1,point5).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.1));
    private final Geometry triangle2 = new Triangle(point1,point3,point5).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.2));
    private final Geometry triangle3 = new Triangle(point3,point7,point4).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.3));
    private final Geometry triangle4 = new Triangle(point3,point6,point7).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.4));
    private final Geometry windows1 = new Square(new Point(-50,100, -70),new Point(10,100, -70),new Point(10,-35, -70),new Point(-50,-35, -70))//
            .setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.1));
    private final Geometry windowsill = new Square(new Point(-50,-40, -100),new Point(100,-40, -100),new Point(100,-40, 0),new Point(-50,-40, 0))//
            .setMaterial(new Material().setKd(0.5).setKr(0.7).setSpreadingR(0.1)).setEmission(new Color(GRAY));
    private final Geometry lintel = new Square(new Point(-50,100, 0),new Point(-50,100, -100),new Point(-50,-40, -100),new Point(-50,-40, 0))//
            .setMaterial(new Material().setKd(0.5)).setEmission(new Color(GRAY));
    private final Geometry sphere4 = new Sphere(3d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.65));
    private final Geometry sphere5 = new Sphere(2d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private final Geometry sphere6 = new Sphere(1d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.85));
    private final Geometry sphere7 = new Sphere(0.5d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.95));
    private final Geometry sphere8 = new Sphere(5d, new Point(0, 5, -60)).setEmission(new Color(200,100,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private final Geometry sphere9 = new Sphere(5d, new Point(8, 0, -50)).setEmission(new Color(0,220,0)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private final Geometry sphere10 = new Sphere(5d, new Point(-8, 10, -60)).setEmission(new Color(0,0,200)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    /** Produce a picture with all the effects */


    @Test
    public void theFinal() {
        scene.geometries.add(sphere4, sphere5,sphere6, sphere7, sphere8, sphere9, sphere10, windows1, windowsill, lintel);
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        /*scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));*/

        camera.setImageWriter(new ImageWriter("Final with adaptive", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene,true)) //
                .setThreadsCount(3)//
                .renderImage() //
                .writeToImage();

    }

    @Test
    public void theFinal1() {
        scene.geometries.add(sphere4, sphere5,sphere6, sphere7, sphere8, sphere9, sphere10, windows1, windowsill, lintel);
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        /*scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(//
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));*/

        camera.setImageWriter(new ImageWriter("Final", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setThreadsCount(3)//
                .renderImage() //
                .writeToImage();

    }

}