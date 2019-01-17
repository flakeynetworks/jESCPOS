package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public interface GraphicsProcessor {

    BufferedImage applyFilter(BufferedImage image, GraphicsFilter filter);
    byte[] convertToArray(BufferedImage image);
} // end of GraphicsProcessor
