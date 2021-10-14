
/* 
Equipo: 
        Diana Laura Ballesteros Valenzuela
        Jose Pablo Medrano Ibarra

*/
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

public class Plugin_Histogram implements PlugInFilter {

    /*
     * 
     * Plugin development that calculates the cumulative histogram of an 8-bit
     * grayscale image and displays it as a new image
     * 
     */

    ImagePlus im;

    public int setup(String args, ImagePlus im) {
        this.im = im;
        return DOES_8G + NO_CHANGES;
    }

    public void run(ImageProcessor ip) {
        int[] hist = ip.getHistogram();
        int k = hist.length;
        int[] suma = new int[k];
        int maxima_A = 0, nueva_A = 0;

        ImageProcessor hip = new ByteProcessor(k, 150);
        hip.setValue(255); // white = 255
        hip.fill();

        suma[0] = hist[0];
        for (int i = 1; i < k; i++) {
            suma[i] = hist[i] + suma[i - 1];
            if (suma[i] > maxima_A) {
                maxima_A = suma[i];
            }
        }

        for (int i = 0; i < k; i++) {
            nueva_A = (150 * suma[i]) / maxima_A;

            for (int j = 150; j > (150 - nueva_A); j--) {
                hip.putPixel(i, j, 0);
            }
        }

        String imTitle = im.getShortTitle();
        String hisTitle = "Histogram of " + imTitle;
        ImagePlus him = new ImagePlus(hisTitle, hip);

        him.show();
    }
}