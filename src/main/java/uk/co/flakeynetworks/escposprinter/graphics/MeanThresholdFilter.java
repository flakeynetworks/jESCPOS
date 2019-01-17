package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public class MeanThresholdFilter implements GraphicsFilter {


    private static final int WHITE_PIXEL = 16777215; // 0xffffff
    private static final int BLACK_PIXEL = 0; // 0x000000

    @Override
    public BufferedImage applyFilter(BufferedImage image) {

        BufferedImage thresholdImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int averageLumin = getAverageLumin(image);

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {

                if((image.getRGB(x, y) & 0xff) < averageLumin) {
                    thresholdImage.setRGB(x, y, BLACK_PIXEL);
                } else {
                    thresholdImage.setRGB(x, y, WHITE_PIXEL);
                } // end of if
            } // end of for
        } // end of for

        return thresholdImage;
    } // end of applyFilter


    private int getAverageLumin(BufferedImage image) {

        int sum = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                sum += image.getRGB(x, y) & 0xff;
            } // end of for
        } // end of for

        return sum / (width * height);
    } // end of getAverageLumin
} // end of MeanThresholdFilter
