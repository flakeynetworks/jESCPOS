package uk.co.flakeynetworks.escposprinter.commands.GS;

import uk.co.flakeynetworks.escposprinter.ESCPOSCommand;
import uk.co.flakeynetworks.escposprinter.ESCPOSPrinter;
import uk.co.flakeynetworks.escposprinter.commands.ESCPOSCommands;

import java.io.IOException;
import java.io.OutputStream;

public class NVGraphic8L implements ESCPOSCommand {


    private final ESCPOSPrinter printer;
    private final int width;
    private final int height;
    private final byte[] image;


    public NVGraphic8L(ESCPOSPrinter printer, int width, int height, byte[] buffer) {

        this.printer = printer;
        this.width = width;
        this.height = height;
        this.image = buffer;
    } // end of constructor


    @Override
    public void execute() {

        OutputStream output = printer.getOutputStream();

        try {
            // Number of bytes = (width * height) / 8 + 11
            int numberOfBytes = image.length + 11;
            int p4 = numberOfBytes / 16777216;
            numberOfBytes = numberOfBytes % 16777216;

            int p3 = numberOfBytes / 65536;
            numberOfBytes = numberOfBytes % 65536;

            int p2 = numberOfBytes / 256;
            numberOfBytes = numberOfBytes % 256;

            int p1 = numberOfBytes;

            // GS
            output.write(ESCPOSCommands.GS);

            // 8L
            output.write(0x38);
            output.write(0x4C);

            // p1
            output.write((byte) p1);

            // p2
            output.write((byte) p2);

            // p3
            output.write((byte) p3);

            // p4
            output.write((byte) p4);

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
