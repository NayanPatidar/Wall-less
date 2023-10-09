package CSharing;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.*;

public class Client {
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
    public static void main(String[] args) throws SocketException {
        datagramSocket = new DatagramSocket(portUDP);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            datagramSocket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String receivedMsg = new String(packet.getData(), 0, packet.getLength());
        if (receivedMsg.startsWith("T:")) {
            String msg = receivedMsg.substring(2);
            System.out.println("Received ->"+msg);
            StringSelection string = new StringSelection(msg);
            clipboard.setContents(string, null);

        }
    }
}
