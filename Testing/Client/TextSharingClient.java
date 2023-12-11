package Client;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.net.*;

public class TextSharingClient implements Runnable{
     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private  String lastCopiedText = "";
    private  String msgFromClient = "";
     int portUDP = 12346;
     InetAddress inetAddress;
     DatagramSocket datagramSocket1;

    public TextSharingClient( InetAddress inetAddress) {
        this.inetAddress  = inetAddress;
    }

    public void run() {
        try {
             datagramSocket1 = new DatagramSocket(portUDP);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        Thread sending = new Thread(() -> {
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
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread receiving = new Thread(() -> {

            while (true) {
                try {
                    datagramSocket1.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String receivedMsg = new String(packet.getData(), 0, packet.getLength());

                if (receivedMsg.startsWith("T:")) {
                    String msg = receivedMsg.substring(2);
                    msgFromClient = msg;
                    System.out.println("Received ->" + msg);
                    StringSelection string = new StringSelection(msg);
                    clipboard.setContents(string, null);
                }
            }
        });

        sending.start();
        receiving.start();

        try {
            receiving.join();
            sending.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToClient(String clipboardText) {
        String msg = "T:" + clipboardText;
        byte[] sendData = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
        try {
            datagramSocket1.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data sent to client: " + clipboardText);
    }
}
