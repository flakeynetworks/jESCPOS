package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public class WeightedThresholdFilter implements GraphicsFilter {


    private static final int WHITE_PIXEL = 16777215; // 0xffffff
    private static final int BLACK_PIXEL = 0; // 0x000000

    private final int weight;
    private final int threshold;

    /**
     *
     * @param thresholdValue 0 - 100
     */
    public WeightedThresholdFilter(int thresholdValue) {

        if(thresholdValue > 100) thresholdValue = 100;
        if(thresholdValue < 0) thresholdValue = 0;

        weight = thresholdValue;
        threshold = (255 / 100) * weight;
    } // end of constructor


    @Override
    public BufferedImage applyFilter(BufferedImage image) {

        BufferedImage thresholdImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {

                if((image.getRGB(x, y) & 0xff) < threshold) {
                    thresholdImage.setRGB(x, y, BLACK_PIXEL);
                } else {
                    thresholdImage.setRGB(x, y, WHITE_PIXEL);
                } // end of if
            } // end of for
        } // end of for

        return thresholdImage;
    } // end of applyFilter]
} // end of MeanThresholdFilter
