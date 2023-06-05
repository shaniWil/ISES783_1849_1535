package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents a light of Ambient Light type
 * @author Raaya Feldmar & Shani Wilamowsky
 */
public class AmbientLight extends Light {

    /**
     * the color of the ambient light
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constructor of ambient light
     * @param Ia the intensity of the light (color)
     * @param Ka attenuation coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * constructor of ambient light
     * @param Ia the intensity of the light (color)
     * @param Ka attenuation coefficient
     */
    public AmbientLight(Color Ia, Double Ka) {
        super(Ia.scale(Ka));
    }

}
