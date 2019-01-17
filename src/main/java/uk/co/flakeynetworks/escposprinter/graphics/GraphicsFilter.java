package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public interface GraphicsFilter {

    BufferedImage applyFilter(BufferedImage image);
} // end of GraphicsFilter
