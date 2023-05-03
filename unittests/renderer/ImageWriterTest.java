package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;
/** Test write to image.
 * @author Raaya Feldmar & Shani Wilamowsky */
class ImageWriterTest {

    @Test
     void testImageWriter() {
        ImageWriter imageWriter = new ImageWriter("yellow and red image", 800, 500);
        for (int i =0; i< imageWriter.getNx(); i++) {
            for (int j = 0; j<imageWriter.getNy();j++) {
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i,j,new Color(93,11,0));
                else
                    imageWriter.writePixel(i,j,new Color(100,100,100));
            }
        }
        imageWriter.writeToImage();
    }
}