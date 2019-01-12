An ESCPOS library written in Java. Just pass in a output stream.

Socket socket = new Socket("10.0.0.52", 9100);

OutputStream stream = socket.getOutputStream();

ESCPOSPrinter printer = new ESCPOSBasicPrinter(stream);
printer.initialize();

printer.newLine()
        .emphasize()
        .center()
        .fontSizeFactor(2)
        .print("HELLO")
        .newLine()
        .alignLeft()
        .fontSizeFactor(2,1)
        .println("World")
        .feedLines(2)
        .cut();
