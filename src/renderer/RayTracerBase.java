package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * represents a ray tracer.
 * trace rays through a scene.
 * finding a color of an object that intersects closest to the ray.
 *
 * @author Raaya Feldmar & Shani Wilamowsky.
 */

public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * constructor of RayTracerBase
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * tracing a ray through a scene and finding the color of the object closest to the head of the ray
     *
     * @param ray the ray to trace the scene with
     * @return the co;or of the object the ray 'sees' first
     */
    public abstract Color traceRay(Ray ray);
}
