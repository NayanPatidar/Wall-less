package ServerLinux;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class TextSharingServer implements Runnable{
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private  String lastCopiedText = "";
    private  String msgFromClient = "";
    int portUDP = 12346;
    InetAddress inetAddress;
    DatagramSocket datagramSocket;
    ArrayList<Clients> clientsArrayList;
    GUI gui = new GUI();

    public TextSharingServer( ArrayList<Clients> clientsArrayList) {//        this.portUDP = portUDP;
        this.clientsArrayList = clientsArrayList;
    }
    @Override
    public void run() {
        try {
            datagramSocket = new DatagramSocket(portUDP);
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
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sending.start();

        while (true) {
                try {
                    datagramSocket.receive(packet);
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
    }
    private  void sendToClient(String clipboardText) {
        String msg = "T:" + clipboardText;
        byte[] sendData = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, clientsArrayList.get(gui.getArrayListIndex()).getInetAddress(), portUDP);
        try {
            datagramSocket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data sent to client: " + clipboardText);
    }
}
