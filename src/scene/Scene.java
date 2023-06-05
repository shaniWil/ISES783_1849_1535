package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents scene
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class Scene {
    //the name of the scene
    public String name;
    //the color of the background
    public Color background;
    //the ambient light
    public AmbientLight ambientLight =  AmbientLight.NONE;
    //the geometries body on the scene
    public Geometries geometries;
    //the list of the light source
    public List<LightSource> lights = new LinkedList<>();

    /**
     * constructor of the scene
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
        background = Color.BLACK;
    }
    /**
     * setter list of lights at Builder Method pattern
     * @param lights
     * @return the scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * setter the background color at Builder Method pattern
     * @param background
     * @return the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter the ambientLight at Builder Method pattern
     * @param ambientLight
     * @return the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter the geometries at Builder Method pattern
     * @param geometries
     * @return the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
