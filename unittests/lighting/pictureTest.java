package lighting;

import static java.awt.Color.*;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;
import scene.Scene;

public class pictureTest {
    private Scene scene = new Scene("Test scene");
    Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150).setVPDistance(80);
    Camera camera1 = new Camera(new Point(-50, 0, -50), new Point(0,0,-50))//
            .setVPSize(150, 150).setVPDistance(80);
    Camera camera2= camera.scaleDegreesXY(20).setVPSize(150, 150).setVPDistance(80);
    Camera camera3= camera.scaleDegreesXY(180).setVPSize(150, 150).setVPDistance(80);
    private Point point1= new Point(10,10,-20);
    private Point point2= new Point(-10,10,-20);
    private Point point3= new Point(10,2.5,-20);
    private Point point4= new Point(2.5,2.5,-20);
    private Point point5= new Point(-10,2.5,-20);
    private Point point6= new Point(10,-10,-20);
    private Point point7= new Point(2.5,-10,-20);
    private Geometry triangle1 = new Triangle(point2,point1,point5).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.1));
    private Geometry triangle2 = new Triangle(point1,point3,point5).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.2));
    private Geometry triangle3 = new Triangle(point3,point7,point4).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.3));
    private Geometry triangle4 = new Triangle(point3,point6,point7).setMaterial(new Material().setKd(0.5).setKt(0.8).setSpreadingT(0.4));
    private Geometry plane1 = new Plane(new Point(0,0, 0), new Vector(0,0,-1)).setMaterial(new Material().setKd(0.2)).setEmission(new Color(BLUE));
    private Geometry plane2R =new Plane(new Point(0,0, -80), new Vector(0,0,1)).setMaterial(new Material().setKd(0.5).setKr(0.8)).setEmission(new Color(BLACK));
    private Geometry plane2T =new Plane(new Point(0,0, -60), new Vector(0,0,1)).setMaterial(new Material().setKd(0.5).setKt(0.8)).setEmission(new Color(BLACK));
    private Geometry plane3 =new Plane(new Point(0,0, -100), new Vector(0,0,1)).setMaterial(new Material().setKd(0.05)).setEmission(new Color(RED));
    private Geometry plane3b =new Plane(new Point(0,0, -150), new Vector(0,0,1)).setMaterial(new Material().setKd(0.05)).setEmission(new Color(RED));

    private Geometry plane4 =new Plane(new Point(-50,0, 0), new Vector(1,0,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(yellow));
    private Geometry plane5 =new Plane(new Point(50,0, 0), new Vector(-1,0,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(35,191,3));
    private Geometry plane6 = new Plane(new Point(0,50, 0), new Vector(0,-1,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(gray));
    private Geometry plane7 = new Plane(new Point(0,-50, 0), new Vector(0,1,0)).setMaterial(new Material().setKd(0.5)).setEmission(new Color(gray));

    private Geometry sphere1 = new Sphere(10d, new Point(-5, -20, -70)).setEmission(new Color(BLACK)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKr(1));
    private Geometry sphere2 = new Sphere(10d, new Point(15, 0, -80)).setEmission(new Color(BLUE)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100));
    private Geometry sphere3 = new Sphere(5d, new Point(15, 0, -40)).setEmission(new Color(RED)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
    private Geometry sphere4 = new Sphere(3d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.65));
    private Geometry sphere5 = new Sphere(2d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private Geometry sphere6 = new Sphere(1d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.85));
    private Geometry sphere7 = new Sphere(0.5d, new Point(0, 40, -60)).setEmission(new Color(220,220,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.95));
    private Geometry sphere8 = new Sphere(5d, new Point(0, 5, -60)).setEmission(new Color(200,100,30)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private Geometry sphere9 = new Sphere(5d, new Point(8, 0, -50)).setEmission(new Color(0,220,0)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    private Geometry sphere10 = new Sphere(5d, new Point(-8, 10, -60)).setEmission(new Color(0,0,200)) //
            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.75));
    /** Produce a picture with all the effects */

    @Test
    public void BeamRays() {
        scene.geometries.add( plane1, plane2R, plane3, plane4, plane5, plane6, plane7);
        scene.lights.add( //
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("project 1 R", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
    @Test
    public void BeamRays2() {
        scene.geometries.add( plane1, plane2T, plane3, plane4, plane5, plane6, plane7);
        scene.lights.add( //
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -60)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("project 1 T", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
    @Test
    public void theFinal() {
        scene.geometries.add(sphere4, sphere5,sphere6, sphere7, sphere8, sphere9, sphere10, triangle1, triangle2, triangle3, triangle4, plane7);
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
                .renderImage() //
                .writeToImage();

    }

    @Test

    public void another() {
        scene.geometries.add( sphere3,//

                plane1, plane2R, plane3, plane4, plane5, plane6, plane7);
        scene.lights.add( //
                new PointLight(new Color(200, 180, 80), new Point(0, 40, -20)) //
                        .setKl(0.0004).setKq(0.0000006));
        //scene.lights.add(new DirectionalLight(new Color(200, 200, 60), new Vector(-1, -1, -2)));

        camera.setImageWriter(new ImageWriter("project 1", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
        /*       camera.setImageWriter(new ImageWriter("the picture", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
                camera1.setImageWriter(new ImageWriter("the picture1", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
        camera2.setImageWriter(new ImageWriter("the picture2", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
        camera3.setImageWriter(new ImageWriter("the picture3", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();*/
    }
    @Test
    public void another2() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(80);
        Camera camera1 = new Camera(new Point(-50, 0, -50), new Point(0,0,-50))//
                .setVPSize(150, 150).setVPDistance(80);
        Camera camera2= camera.scaleDegreesXY(20).setVPSize(150, 150).setVPDistance(80);
        Camera camera3= camera.scaleDegreesXY(180).setVPSize(150, 150).setVPDistance(80);

        scene.geometries.add( sphere2,//
                plane1, plane2T, plane3, plane4, plane5, plane6, plane7);
        scene.lights.add( //
                new PointLight(new Color(200, 180, 80), new Point(-20, -40, -30)) //
                        .setKl(0.0004).setKq(0.0000006));


        camera.setImageWriter(new ImageWriter("project 2", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }

}