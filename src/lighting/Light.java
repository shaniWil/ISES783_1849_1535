package lighting;

import primitives.Color;

/** This abstract class will serve all lights source.
 * @author Raaya Feldmar & Shani Wilamowsky */
abstract class Light {
     private Color intensity;

 protected Light(Color intensity) {
  this.intensity = intensity;
 }

 /**
  * @return the intensity of the light.
  */
 public Color getIntensity() {
  return intensity;
 }
}
