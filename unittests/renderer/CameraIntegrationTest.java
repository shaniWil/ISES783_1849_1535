package renderer;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CameraIntegrationTest {
    Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1), new Vector(0,1,0))
            .setVPSize(3,3)
            .setVPDistance(1);
    Camera camera2 = new Camera(new Point(0,0,0.5),new Vector(0,0,-1), new Vector(0,1,0))
            .setVPSize(3,3)
            .setVPDistance(1);

    int generalIntegration(Geometry geometry, Camera camera,int nX, int nY) {
        int count = 0;
        for (int i =0; i<nY; i++)
        {
            for (int j =0; j<nX; j++){
                Ray ray = camera.constructRay(nX,nY,j,i);
                if (geometry.findIntsersections(ray) != null)
                    count+= geometry.findIntsersections(ray).size();
            }
        }
        return count;
    }
    @Test
    void sphereTest(){
        //TC01:test intersection with sphere after the view plane
        Sphere sphere1 = new Sphere(1,new Point(0,0,-3));
        assertEquals(2,
                generalIntegration(sphere1, camera,3,3),
                "ERROR: sphereTest() wrong amount of integrations");
        //TC02:test intersection with sphere that contain all the view plane
        Sphere sphere2 = new Sphere(2.5,new Point(0,0,-2.5));
        assertEquals(18,
                generalIntegration(sphere2,camera2, 3,3),
                "ERROR: sphereTest() wrong amount of integrations");
        //TC03:test intersection with sphere that contain partly of the view plane
        Sphere sphere3 = new Sphere(2,new Point(0,0,-2));
        assertEquals(10,
                generalIntegration(sphere3,camera2, 3,3),
                "ERROR: sphereTest() wrong amount of integrations");
        //TC04:test intersection with sphere that contain all the view plane and the camera
        Sphere sphere4 = new Sphere(4,new Point(0,0,-1));
        assertEquals(9,
                generalIntegration(sphere4, camera, 3,3),
                "ERROR: sphereTest() wrong amount of integrations");
        //TC05:test intersection with sphere before the view plane
        Sphere sphere5 = new Sphere(0.5,new Point(0,0,1));
        assertEquals(0,
                generalIntegration(sphere5,camera, 3,3),
                "ERROR: sphereTest() wrong amount of integrations");
         }
    @Test
    void triangleTest(){

        //TC01:test intersection with small triangle
        Triangle triangle1 = new Triangle(new Point(1, -1,-2),new Point(0, 1,-2), new Point(-1, -1,-2));
        assertEquals(1,
                generalIntegration(triangle1, camera,3,3),
                "ERROR: triangleTest() wrong amount of integrations");
        //TC02:test intersection with big triangle
        Triangle triangle2 = new Triangle(new Point(1, -1,-2),new Point(0, 20,-2), new Point(-1, -1,-2));
        assertEquals(2,
                generalIntegration(triangle2,camera, 3,3),
                "ERROR: triangleTest() wrong amount of integrations");

    }
    @Test
    void planeTest(){

        //TC01:test intersection with small triangle
        Plane plane1 = new Plane(new Point(0,0,-3),new Vector(0,0,1));
        assertEquals(9,
                generalIntegration(plane1, camera,3,3),
                "ERROR: planeTest() wrong amount of integrations");
        //TC02:
        Plane plane2 = new Plane(new Point(0,0,-3),new Vector(0,1,-2));
        assertEquals(9,
                generalIntegration(plane2,camera, 3,3),
                "ERROR: planeTest() wrong amount of integrations");
        //TC03:
        Plane plane3 = new Plane(new Point(0,0,-3),new Vector(0,2,-1));
        assertEquals(6,
                generalIntegration(plane3,camera, 3,3),
                "ERROR: planeTest() wrong amount of integrations");

    }

}