import org.junit.Before;
import org.junit.Test;
import uk.co.flakeynetworks.escposprinter.graphics.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGraphicsProcessor {

    private GraphicsProcessor processor;
    private BufferedImage flakeynetworksLogo;

    @Before
    public void setup() {

        processor = new BasicProcessor();

        try {
            flakeynetworksLogo = ImageIO.read(new File("sampleGraphics", "flakeynetworks_logo_small.jpg"));
        } catch (IOException e) {

            e.printStackTrace();
            assert false;
        } // end of catch
    } // end of setup


    @Test
    public void testGrayscale() {

        BufferedImage greyscale = processor.applyFilter(flakeynetworksLogo, new GreyscaleFilter());

        try {
            ImageIO.write(greyscale, "jpg", new File("greyscale.jpg"));
        } catch (IOException e) {

            e.printStackTrace();
            assert false;
        } // end of catch
    } // end of testGreyscale


    @Test
    public void testMeanThreshold() {

        BufferedImage image = processor.applyFilter(flakeynetworksLogo, new GreyscaleFilter());
        BufferedImage threshold = processor.applyFilter(image, new MeanThresholdFilter());

        try {
            ImageIO.write(threshold, "jpg", new File("thresholdMean.jpg"));
        } catch (IOException e) {

            e.printStackTrace();
            assert false;
        } // end of catch
    } // end of testThreshold


    @Test
    public void testMedianThreshold() {

        BufferedImage image = processor.applyFilter(flakeynetworksLogo, new GreyscaleFilter());
        BufferedImage threshold = processor.applyFilter(image, new MedianThresholdFilter());

        try {
            ImageIO.write(threshold, "jpg", new File("thresholdMedian.jpg"));
        } catch (IOException e) {

            e.printStackTrace();
            assert false;
        } // end of catch
    } // end of testThreshold


    @Test
    public void testWeightThreshold() {

        int weightValue = 95;

        BufferedImage image = processor.applyFilter(flakeynetworksLogo, new GreyscaleFilter());
        BufferedImage threshold = processor.applyFilter(image, new WeightedThresholdFilter(weightValue));

        try {
            ImageIO.write(threshold, "jpg", new File("thresholdWeight_" + weightValue + ".jpg"));
        } catch (IOException e) {

            e.printStackTrace();
            assert false;
        } // end of catch
    } // end of testThreshold


    @Test
    public void testConvertToByte() {

        BufferedImage image = processor.applyFilter(flakeynetworksLogo, new GreyscaleFilter());
        BufferedImage threshold = processor.applyFilter(image, new MeanThresholdFilter());

        byte[] buffer = processor.convertToArray(threshold);
    } // end of testConvertToByte
} // end of TestGraphicsProcessor
