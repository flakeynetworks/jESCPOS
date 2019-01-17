import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.flakeynetworks.escposprinter.ESCPOSBasicPrinter;
import uk.co.flakeynetworks.escposprinter.ESCPOSPrinter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestPrinter {

    private ESCPOSPrinter printer;
    private BufferedImage flakeynetworksLogo;

    @Before
    public void setup() {

        try {
            Socket socket = new Socket("10.0.0.52", 9100);

            OutputStream stream = socket.getOutputStream();

            Thread thread = new Thread() {

                @Override
                public void run() {

                    try {
                        InputStream is = socket.getInputStream();
                        byte[] read = new byte[1024];
                        int readBytes = 0;
                        while((readBytes = is.read(read)) < 0) {

                            byte[] sub = new byte[readBytes];
                            for(int i = 0; i < readBytes; i++)
                                sub[i] = read[i];

                            System.out.print(sub);
                        }
                    } catch(IOException e) { e.printStackTrace(); } // end of catch
                }
            };

            thread.start();

            socket.getInputStream();

            printer = new ESCPOSBasicPrinter(stream);
            printer.initialize();


            try {
                flakeynetworksLogo = ImageIO.read(new File("sampleGraphics", "flakeynetworks_logo_small.jpg"));
            } catch (IOException e) {

                e.printStackTrace();
                assert false;
            } // end of catch
        } catch (IOException e) { assert false; } // end of catch
    } // end of setup

    @Test
    public void testPrintText() {

        printer.printImage(flakeynetworksLogo);
        printer.feedAndCut();
    } // end of testPrintText


    @After
    public void waitForBufferToClear() {

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // end of catch
    } // end of waitForBufferToClear
} // end of TestPrinter
