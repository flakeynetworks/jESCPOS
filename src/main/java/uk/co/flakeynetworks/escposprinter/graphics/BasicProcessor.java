package uk.co.flakeynetworks.escposprinter.graphics;

import java.awt.image.BufferedImage;

public class BasicProcessor implements GraphicsProcessor {


    @Override
    public BufferedImage applyFilter(BufferedImage image, GraphicsFilter filter) {

        return filter.applyFilter(image);
    } // end of convertToGreyscale


    @Override
    public byte[] convertToArray(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        int numberOfBytes = (width * height) / 8;
        if(((width * height) % 8) > 0) numberOfBytes++;

        byte[] buffer = new byte[numberOfBytes];

        int byteCounter = 0;
        int bitCounter = 0;
        int currentByte = 0x00;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {

                // Check if we have filled a byte, then add this to the array and reset values
                if(bitCounter > 7) {

                    try {
                        buffer[byteCounter] = (byte) currentByte;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    byteCounter++;
                    currentByte = 0;
                    bitCounter = 0;
                } // end of if


                int value = image.getRGB(x, y) & 0xff;
                currentByte = currentByte << 1;

                // Check if this needs to be black
                if(value > 128)
                    currentByte = currentByte | 0x01;

                bitCounter++;
            } // end of for
        } // end of for

        // Fill in the last byte
        int positionsToShift = 7 - bitCounter;
        if(positionsToShift < 0)
            currentByte = currentByte << positionsToShift;
        buffer[byteCounter] = (byte) currentByte;

        return buffer;
    } // end of convertToArray
} // end of BasicProcessor
