package CSharing;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.*;

public class Server {
    static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private static String lastCopiedText = "";
    static int portUDP = 12345;
    static InetAddress inetAddress;
    {
        try {
            inetAddress = InetAddress.getByName("10.200.233.31");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    static DatagramSocket datagramSocket;

    public static void main(String[] args) throws SocketException, InterruptedException {

        datagramSocket = new DatagramSocket(portUDP);

        Thread threadOne = new Thread(() -> {
            Transferable contents = clipboard.getContents(null);
            if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String clipboardText = null;
                try {
                    clipboardText = "T:'" + (String) contents.getTransferData(DataFlavor.stringFlavor) + "'";
                } catch (UnsupportedFlavorException | IOException e) {
                    throw new RuntimeException(e);
                }

                if (!clipboardText.equals(lastCopiedText)) {
                    lastCopiedText = clipboardText;
                    sendToClient(clipboardText);
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadOne.start();
        threadOne.join();
        Thread threadTwo = new Thread(() -> {

        });
    }

    private static void sendToClient(String clipboardText) {
        byte[] sendData = clipboardText.getBytes();

        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
        try {
            datagramSocket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data sent to client: " + clipboardText);
    }
}
