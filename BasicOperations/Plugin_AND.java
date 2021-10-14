import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;

public class Plugin_AND implements PlugInFilter {

    /*
     * 
     * Implementation of the AND operation on 8-bit grayscale images with the input
     * of an integer value
     * 
     */

    public int setup(String args, ImagePlus im) {
        return DOES_8G;
    }

    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();
        int valor = 25;
        GenericDialog gd = new GenericDialog("New Image");
        gd.addNumericField("Valor", valor, 0);
        gd.showDialog();
        if (gd.wasCanceled()) {
            return;
        }
        valor = (int) gd.getNextNumber();

        ij.IJ.log("valor: " + valor);
        // iterate over all image coordinates
        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                int p = ip.getPixel(u, v);
                ip.putPixel(u, v, (p & valor));

            }
        }
    }

}