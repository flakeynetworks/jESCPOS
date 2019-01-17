package uk.co.flakeynetworks.escposprinter;

import uk.co.flakeynetworks.escposprinter.commands.ESCPOSCommands;
import uk.co.flakeynetworks.escposprinter.commands.GS.NVGraphic8L;
import uk.co.flakeynetworks.escposprinter.commands.GS.NVGraphicL;
import uk.co.flakeynetworks.escposprinter.graphics.BasicProcessor;
import uk.co.flakeynetworks.escposprinter.graphics.GraphicsProcessor;
import uk.co.flakeynetworks.escposprinter.graphics.GreyscaleFilter;
import uk.co.flakeynetworks.escposprinter.graphics.MeanThresholdFilter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ESCPOSBasicPrinter implements ESCPOSPrinter {

    private OutputStream output;

    public ESCPOSBasicPrinter(OutputStream outputStream) {

        output = outputStream;
    } // end of constructor


    @Override
    public ESCPOSPrinter feedLine() {

        try {

            // Epson command LF
            output.write(0x0A);
            output.flush();
        } catch (IOException ignored) {} // end of catch

        return this;
    } // end of feedLine


    @Override
    // TODO need to check
    public ESCPOSPrinter feedAndCut() {

        try {

            // Epson command ESCPOSCommands.GS "V"
            output.write(ESCPOSCommands.GS);
            output.write(0x56);
            output.write(0x42);
            output.write(0x00);

            output.flush();
        } catch (IOException ignored) {} // end of catch

        return this;
    } // end of feedAndCut


    @Override
    // TODO NEED TO CHECK THIS!
    public ESCPOSPrinter soundBuzzer() {

        try {

            // Epson command DLE DC4 (fn=3)
            output.write(0x10);
            output.write(0x14);
            output.write(0x03);

            // a
            output.write(0x01);
            output.write(0x10);
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of soundsBuzzer


    public ESCPOSPrinter printImage(int width, int height, byte[] image) {

        new NVGraphic8L(this, width, height, image).execute();

        return this;
    } // end of printImage


    @Override
    public OutputStream getOutputStream() { return output; } // end of getOutputStream


    @Override
    public ESCPOSPrinter printImage(BufferedImage image) {

        // Convert the image to greyscale
        GraphicsProcessor processor = new BasicProcessor();
        image = processor.applyFilter(image, new GreyscaleFilter());
        image = processor.applyFilter(image, new MeanThresholdFilter());

        byte[] data = processor.convertToArray(image);
        printImage(image.getWidth(), image.getHeight(), data);
        return this;
    } // end of printImage


    @Override
    public ESCPOSPrinter cut() {

        // Cut command
        try {
            output.write(ESCPOSCommands.GS);
            output.write(0x56);

            // Full cut
            output.write(0x00);

            output.flush();
            // Partial cut
            //stream.write(0x01);
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of cut


    @Override
    public ESCPOSPrinter underline() { return underline(true); } // end of underline


    @Override
    public ESCPOSPrinter tab() {

        // Tab command
        // Epson command HT
        try {
            output.write(0x09);
            output.flush();
            // Partial cut
            //stream.write(0x01);
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of tab


    @Override
    public ESCPOSPrinter underline(boolean thickLine) {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x2D);

            if(thickLine)
                output.write(2);
            else
                output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of underline


    @Override
    public ESCPOSPrinter flush() {

        try {
            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of flush


    @Override
    public ESCPOSPrinter underlineEnd() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x2D);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of underline


    /**
     * Clears the data in the print buffer and resets the printer modes to the modes that were
     * in effect when the power was turned on.
     * @return
     */
    @Override
    public ESCPOSPrinter initialize() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x40);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of initialize


    @Override
    public ESCPOSPrinter emphasize() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x45);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of emphasize


    @Override
    public ESCPOSPrinter emphasizeEnd() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x45);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of emphasize


    @Override
    public ESCPOSPrinter center() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x61);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of center


    @Override
    public ESCPOSPrinter alignLeft() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x61);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of leftAlign


    @Override
    public ESCPOSPrinter doubleStrike() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x47);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of doubleStrike


    @Override
    public ESCPOSPrinter doubleStrikeEnd() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x61);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of doubleStrikeEnd


    @Override
    public ESCPOSPrinter alignRight() {

        try {
            output.write(ESCPOSCommands.ESC);
            output.write(0x61);
            output.write(2);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of rightAlign


    /**
     *
     * @param  numberOfLines number of lines to feed 0 - 255
     */
    @Override
    public ESCPOSPrinter feedLines(int numberOfLines) {

        // Validate the number of lines
        if(numberOfLines < 0 || numberOfLines > 255) throw new IllegalArgumentException("Number of lines must be between 0 and 255 inclusive");

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x64);
            output.write(numberOfLines); // number of lines to feed

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of feedLines


    @Override
    public ESCPOSPrinter newLine() {

        try {
            output.write("\n".getBytes());
            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of newLine


    @Override
    public ESCPOSPrinter useFontA() { return useFont(0); } // end of useFontA
    @Override
    public ESCPOSPrinter useFontB() { return useFont(1); } // end of useFontB
    @Override
    public ESCPOSPrinter useFontC() { return useFont(2); } // end of useFontC
    @Override
    public ESCPOSPrinter useFontD() { return useFont(3); } // end of useFontD
    @Override
    public ESCPOSPrinter useFontE() { return useFont(4); } // end of useFontE
    @Override
    public ESCPOSPrinter useSpecialFontA() { return useFont(97); } // end of useSpecialFontA
    @Override
    public ESCPOSPrinter useSpecialFontB() { return useFont(98); } // end of useSpecialFontB


    @Override
    public ESCPOSPrinter rotate90() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x56);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of rotate90CCW


    @Override
    public ESCPOSPrinter unrotate90() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x56);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of rotate90CCW


    @Override
    public ESCPOSPrinter flip180() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x7B);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of flip180


    @Override
    public ESCPOSPrinter unflip180() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x7B);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of flip180


    private ESCPOSPrinter useFont(int fontNumber) {

        try {
            // Feed lines
            output.write(ESCPOSCommands.ESC);
            output.write(0x4D);
            output.write(fontNumber);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of useFont


    /**
     *
     * @param widthFactor 1 - 8 1x, 2x, 3x .. 8x
     * @param heightFactor 1 - 8 1x, 2x, 3x .. 8x
     * @return
     */
    @Override
    public ESCPOSPrinter fontSizeFactor(int widthFactor, int heightFactor) {

        if(widthFactor < 1) widthFactor = 1;
        else if(widthFactor > 8) widthFactor = 8;

        if(heightFactor < 1) heightFactor = 1;
        else if(heightFactor > 8) heightFactor = 8;

        int n = heightFactor - 1;

        if(widthFactor > 1)
            n += 32 * (widthFactor - 1);

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x21);
            output.write(n);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of fontSizeFactor


    /**
     *
     * @param factor 1 - 8 1x, 2x, 3x .. 8x
     * @return
     */
    @Override
    public ESCPOSPrinter fontSizeFactor(int factor) {
        return fontSizeFactor(factor, factor);
    } // end of fontSizeFactor


    @Override
    public ESCPOSPrinter whiteOnBlack() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x42);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of whiteOnBlack


    @Override
    public ESCPOSPrinter blackOnWhite() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x42);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of blackOnWhite


    @Override
    public ESCPOSPrinter print(String text) {

        try {
            output.write(text.getBytes());
            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of write


    @Override
    public ESCPOSPrinter println(String test) {

        print(test);
        newLine();

        return this;
    } // end of println


    @Override
    public ESCPOSPrinter smoothingOn() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x62);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of smoothingOn

    
    @Override
    public ESCPOSPrinter smoothingOff() {

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x62);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of smoothingOff


    @Override
    public ESCPOSPrinter printBarcode(String contents) {

        try {
            // Feed lines
            output.write(ESCPOSCommands.GS);
            output.write(0x6B);
            output.write(4);
            output.write(contents.getBytes());
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of printBarcode
} // end of ESCPOSPrinter
