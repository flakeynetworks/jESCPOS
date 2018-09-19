import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class main {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("10.0.0.51", 9100);

            OutputStream stream = socket.getOutputStream();

            ESCPOSPrinter printer = new ESCPOSPrinter(stream);

            printer.initialize();

            printer.printBarcode("*00062*").feedLines(5);

            printer.cut();
        } catch (IOException e) {
            e.printStackTrace();
        } // end of catch


        /*
        //Print a registered bitmap.
        ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|1B");

        // To printout out a barcode
        //ptr.printBarCode(POSPrinterConst.PTR_S_RECEIPT, "JS-03-22", POSPrinterConst.PTR_BCS_Code128, 30, ptr.getRecLineWidth(), POSPrinterConst.PTR_BC_CENTER, POSPrinterConst.PTR_BC_TEXT_BELOW);
        */
    } // end of main
} // end of Main
