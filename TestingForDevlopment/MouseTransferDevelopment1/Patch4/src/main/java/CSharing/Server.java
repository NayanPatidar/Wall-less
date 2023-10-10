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
    private static String msgFromClient = "";
    static int portUDP = 12345;
    static InetAddress inetAddress;
    static {
        try {
            inetAddress = InetAddress.getByName("10.200.233.31");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    static DatagramSocket datagramSocket;

    public static void main(String[] args) throws SocketException, InterruptedException {

        try {
            datagramSocket = new DatagramSocket(portUDP);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        Thread threadOne = new Thread(() -> {
            while (true) {
                Transferable contents = clipboard.getContents(null);
                if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String clipboardText = null;
                    try {
                        clipboardText = (String) contents.getTransferData(DataFlavor.stringFlavor);
                    } catch (UnsupportedFlavorException | IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (!clipboardText.equals(lastCopiedText) && !clipboardText.equals(msgFromClient) && !clipboardText.isEmpty()) {
                        lastCopiedText = clipboardText;
                        sendToClient(clipboardText);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadOne.start();
        threadOne.join();
//        Thread threadTwo = new Thread(() -> {
//
//        });
    }

    private static void sendToClient(String clipboardText) {
        String msg = "T:" + clipboardText;
        byte[] sendData = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
        try {
            datagramSocket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data sent to client: " + clipboardText);
    }
}
