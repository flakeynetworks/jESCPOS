package uk.co.flakeynetworks.escposprinter.commands.GS;

import uk.co.flakeynetworks.escposprinter.ESCPOSBasicPrinter;
import uk.co.flakeynetworks.escposprinter.ESCPOSCommand;
import uk.co.flakeynetworks.escposprinter.ESCPOSPrinter;
import uk.co.flakeynetworks.escposprinter.commands.ESCPOSCommands;

import java.io.IOException;
import java.io.OutputStream;

public class NVGraphicL implements ESCPOSCommand {


    private final ESCPOSPrinter printer;
    private final int width;
    private final int height;
    private final byte[] image;


    public NVGraphicL(ESCPOSPrinter printer, int width, int height, byte[] buffer) {

        this.printer = printer;
        this.width = width;
        this.height = height;
        this.image = buffer;
    } // end of constructor


    @Override
    public void execute() {

        // Image is too large for this command to support. Try using NVGraphic8L
        if(image.length > 65523) return;

        OutputStream output = printer.getOutputStream();

        try {
            // Number of bytes = (width * height) / 8 + 11
            int numberOfBytes = image.length + 11;
            int pH = numberOfBytes / 256;
            int pL = numberOfBytes % 256;

            // GS
            output.write(ESCPOSCommands.GS);

            // (L
            output.write(0x28);
            output.write(0x4C);

            // pL
            output.write((byte) pL);

            // pH
            output.write((byte) pH);

            // m
            output.write(0x30);

            //fn
            output.write(0x43);

            //a
            output.write(0x30);

            //kc1/Kc
            output.write(0x47);
            output.write(0x31);

            //b
            output.write(0x01);

            //xL
            output.write((byte) width);

            //xH
            output.write(0x00);

            // yL
            output.write((byte) height);

            // yH
            output.write(0x00);

            // c
            output.write(0x31);

            // d ... dk
            output.write(image);


            // Send command to print the image
            output.write(ESCPOSCommands.GS);
            // (
            output.write(0x28);
            // L
            output.write(0x4C);
            output.write(0x06);
            output.write(0x00);
            output.write(0x30);
            output.write(0x45);
            // G1
            output.write(0x47);
            output.write(0x31);
            //x
            output.write(0x01);
            //y
            output.write(0x01);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        } // end of catch
    } // end of execute
} // end of NVGraphicL
