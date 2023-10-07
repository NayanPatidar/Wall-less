package ServerLinux;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Data implements Runnable{
    DatagramSocket datagramSocket;
    InetAddress inetAddress;
    int portUDP;
    private  String lastCopiedText = "";

    public Data(DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
        this.portUDP = portUDP;
    }

    @Override
    public void run() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        while (true){
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
        }
    }

    private void sendToClient(String clipboardText) {
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
