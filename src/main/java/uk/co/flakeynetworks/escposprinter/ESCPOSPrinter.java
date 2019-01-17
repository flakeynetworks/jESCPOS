package uk.co.flakeynetworks.escposprinter;

import java.awt.image.BufferedImage;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface ESCPOSPrinter {

    ESCPOSPrinter feedLine();
    ESCPOSPrinter feedAndCut();
    ESCPOSPrinter cut();
    ESCPOSPrinter underline();
    ESCPOSPrinter underline(boolean thickLine);
    ESCPOSPrinter underlineEnd();
    ESCPOSPrinter initialize();
    ESCPOSPrinter emphasize();
    ESCPOSPrinter emphasizeEnd();
    ESCPOSPrinter center();
    ESCPOSPrinter alignLeft();
    ESCPOSPrinter doubleStrike();
    ESCPOSPrinter doubleStrikeEnd();
    ESCPOSPrinter alignRight();
    ESCPOSPrinter feedLines(int numberOfLines);
    ESCPOSPrinter newLine();
    ESCPOSPrinter useFontA();
    ESCPOSPrinter useFontB();
    ESCPOSPrinter useFontC();
    ESCPOSPrinter useFontD();
    ESCPOSPrinter useFontE();
    ESCPOSPrinter useSpecialFontA();
    ESCPOSPrinter useSpecialFontB();
    ESCPOSPrinter rotate90();
    ESCPOSPrinter unrotate90();
    ESCPOSPrinter flip180();
    ESCPOSPrinter unflip180();
    ESCPOSPrinter tab();
    ESCPOSPrinter flush();
    ESCPOSPrinter soundBuzzer();
    /**
     *
     * @param widthFactor 1 - 8 1x, 2x, 3x .. 8x
     * @param heightFactor 1 - 8 1x, 2x, 3x .. 8x
     * @return ESCPOSPrinter reference to self for chaining commands
     */
    ESCPOSPrinter fontSizeFactor(int widthFactor, int heightFactor);
    ESCPOSPrinter fontSizeFactor(int factor);
    ESCPOSPrinter whiteOnBlack();
    ESCPOSPrinter blackOnWhite();
    ESCPOSPrinter print(String text);
    ESCPOSPrinter println(String text);
    ESCPOSPrinter smoothingOn();
    ESCPOSPrinter smoothingOff();
    ESCPOSPrinter printBarcode(String contents);
    ESCPOSPrinter printImage(BufferedImage image);
    ESCPOSPrinter printImage(int width, int height, byte[] image);
} // end of ESCPOSPrinter
