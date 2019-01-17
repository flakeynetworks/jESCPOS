package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public class GreyscaleFilter implements GraphicsFilter {

    @Override
    public BufferedImage applyFilter(BufferedImage image) {

        BufferedImage greyscale = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {

                int pixel = image.getRGB(x, y);

                int blue = pixel & 0xff;
                int green = (pixel & 0xff00) >> 8;
                int red = (pixel & 0xff0000) >> 16;

                int average = (blue + green + red) / 3;

                greyscale.setRGB(x, y, average << 16 | average << 8 | average);
            } // end of for
        } // end of for

        return greyscale;
    } // end of applyFilter
} // end of GreyscaleFilter
