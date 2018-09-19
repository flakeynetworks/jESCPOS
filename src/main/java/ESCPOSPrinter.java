import java.io.IOException;
import java.io.OutputStream;

public class ESCPOSPrinter {


    private OutputStream output;


    public ESCPOSPrinter(OutputStream outputStream) {

        output = outputStream;
    } // end of constructor



    public ESCPOSPrinter feedLine() {

        try {
            output.write(0x0A);
            output.flush();
        } catch (IOException ignored) {} // end of catch

        return this;
    } // end of feedLine


    public ESCPOSPrinter cut() {

        // Cut command
        try {
            output.write(0x1D);
            output.write(0x56);

            // Full cut
            output.write(0x00);

            output.flush();
            // Partial cut
            //stream.write(0x01);
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of cut


    public ESCPOSPrinter underline() { return underline(true); } // end of underline


    public ESCPOSPrinter underline(boolean thickLine) {

        try {
            output.write(0x1B);
            output.write(0x2D);

            if(thickLine)
                output.write(2);
            else
                output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of underline


    public ESCPOSPrinter underlineEnd() {

        try {
            output.write(0x1B);
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
    public ESCPOSPrinter initialize() {

        try {
            output.write(0x1B);
            output.write(0x40);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of initialize


    public ESCPOSPrinter emphasize() {

        try {
            output.write(0x1B);
            output.write(0x45);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of emphasize

    public ESCPOSPrinter emphasizeEnd() {

        try {
            output.write(0x1B);
            output.write(0x45);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of emphasize


    public ESCPOSPrinter center() {

        try {
            output.write(0x1B);
            output.write(0x61);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of center


    public ESCPOSPrinter alignLeft() {

        try {
            output.write(0x1B);
            output.write(0x61);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of leftAlign


    public ESCPOSPrinter doubleStrike() {

        try {
            output.write(0x1B);
            output.write(0x47);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of doubleStrike


    public ESCPOSPrinter doubleStrikeEnd() {

        try {
            output.write(0x1B);
            output.write(0x61);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of doubleStrikeEnd


    public ESCPOSPrinter alignRight() {

        try {
            output.write(0x1B);
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
    public ESCPOSPrinter feedLines(int numberOfLines) {

        // Validate the number of lines
        if(numberOfLines < 0 || numberOfLines > 255) throw new IllegalArgumentException("Number of lines must be between 0 and 255 inclusive");

        try {
            // Feed lines
            output.write(0x1B);
            output.write(0x64);
            output.write(numberOfLines); // number of lines to feed

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of feedLines


    public ESCPOSPrinter newLine() {

        try {
            output.write("\n".getBytes());
            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of newLine


    public ESCPOSPrinter useFontA() { return useFont(0); } // end of useFontA
    public ESCPOSPrinter useFontB() { return useFont(1); } // end of useFontB
    public ESCPOSPrinter useFontC() { return useFont(2); } // end of useFontC
    public ESCPOSPrinter useFontD() { return useFont(3); } // end of useFontD
    public ESCPOSPrinter useFontE() { return useFont(4); } // end of useFontE
    public ESCPOSPrinter useSpecialFontA() { return useFont(97); } // end of useSpecialFontA
    public ESCPOSPrinter useSpecialFontB() { return useFont(98); } // end of useSpecialFontB


    public ESCPOSPrinter rotate90() {

        try {
            // Feed lines
            output.write(0x1B);
            output.write(0x56);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of rotate90CCW


    public ESCPOSPrinter unrotate90() {

        try {
            // Feed lines
            output.write(0x1B);
            output.write(0x56);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of rotate90CCW


    public ESCPOSPrinter flip180() {

        try {
            // Feed lines
            output.write(0x1B);
            output.write(0x7B);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of flip180


    public ESCPOSPrinter unflip180() {

        try {
            // Feed lines
            output.write(0x1B);
            output.write(0x7B);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of flip180


    private ESCPOSPrinter useFont(int fontNumber) {

        try {
            // Feed lines
            output.write(0x1B);
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
            output.write(0x1D);
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
    public ESCPOSPrinter fontSizeFactor(int factor) {
        return fontSizeFactor(factor, factor);
    } // end of fontSizeFactor


    public ESCPOSPrinter whiteOnBlack() {

        try {
            // Feed lines
            output.write(0x1D);
            output.write(0x42);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of whiteOnBlack


    public ESCPOSPrinter blackOnWhite() {

        try {
            // Feed lines
            output.write(0x1D);
            output.write(0x42);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of blackOnWhite



    public ESCPOSPrinter print(String text) {

        try {
            output.write(text.getBytes());
            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of write


    public ESCPOSPrinter println(String test) {

        print(test);
        newLine();

        return this;
    } // end of println


    public ESCPOSPrinter smoothingOn() {

        try {
            // Feed lines
            output.write(0x1D);
            output.write(0x62);
            output.write(1);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of smoothingOn


    public ESCPOSPrinter smoothingOff() {

        try {
            // Feed lines
            output.write(0x1D);
            output.write(0x62);
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of smoothingOff


    public ESCPOSPrinter printBarcode(String contents) {

        try {
            // Feed lines
            output.write(0x1D);
            output.write(0x6B);
            output.write(4);
            output.write(contents.getBytes());
            output.write(0);

            output.flush();
        } catch (IOException ignored) { } // end of catch

        return this;
    } // end of printBarcode
} // end of ESCPOSPrinter
