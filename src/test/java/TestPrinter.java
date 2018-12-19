import org.junit.Before;
import org.junit.Test;
import uk.co.flakeynetworks.escposprinter.ESCPOSBasicPrinter;
import uk.co.flakeynetworks.escposprinter.ESCPOSPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestPrinter {

    private ESCPOSPrinter printer;

    @Before
    public void setup() {

        try {
            Socket socket = new Socket("10.0.0.51", 9100);

            OutputStream stream = socket.getOutputStream();

            printer = new ESCPOSBasicPrinter(stream);
            printer.initialize();
        } catch (IOException e) { assert false; } // end of catch
    } // end of setup

    @Test
    public void testPrintText() {

        printer.printBarcode("*00062*").feedLines(5);
        printer.cut();

        /*
        //Print a registered bitmap.
        ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|1B");

        // To printout out a barcode
        //ptr.printBarCode(POSPrinterConst.PTR_S_RECEIPT, "JS-03-22", POSPrinterConst.PTR_BCS_Code128, 30, ptr.getRecLineWidth(), POSPrinterConst.PTR_BC_CENTER, POSPrinterConst.PTR_BC_TEXT_BELOW);
        */
    } // end of testPrintText
} // end of TestPrinter
