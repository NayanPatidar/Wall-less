package KeyboardConnection.Server;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MyKeyListener implements KeyListener {
    private boolean altPressed = false;
    private boolean ctrlPressed = false;
    private boolean tPressed = false;

    Socket socket;
    PrintWriter out ;;


    public MyKeyListener(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String altPacketPress = "K:18";
        String ctrlPacketPress = "K:17";
        String APacketPress = "K:65";
        String shiftPacketPress = "K:16";

//        byte[] bytes_altPress = altPacketPress.getBytes();
//        byte[] bytes_ctrlPress = ctrlPacketPress.getBytes();
//        byte[] bytes_TPress = TPacketPress.getBytes();
//        byte[] bytes_shiftPress = shiftPacketPress.getBytes();

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ALT) {
            System.out.println("ALT is Pressed");
            out.println(altPacketPress);
        } else if (keyCode == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
            out.println(ctrlPacketPress);
        } else if (keyCode == KeyEvent.VK_A) {
            tPressed = true;
            out.println(APacketPress);
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            out.println(shiftPacketPress);
        }

        checkKeys();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String altPacketRelease = "K:18'";
        String ctrlPacketRelease = "K:17'";
        String APacketRelease = "K:65'";
        String shiftPacketRelease = "K:16'";

//        byte[] bytes_altRelease = altPacketRelease.getBytes();
//        byte[] bytes_ctrlRelease = ctrlPacketRelease.getBytes();
//        byte[] bytes_TRelease = TPacketRelease.getBytes();
//        byte[] bytes_shiftRelease = shiftPacketRelease.getBytes();

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ALT) {
            altPressed = false;
            out.println(altPacketRelease);
        } else if (keyCode == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
            out.println(ctrlPacketRelease);
        } else if (keyCode == KeyEvent.VK_A) {
            tPressed = false;
            out.println(APacketRelease);
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            out.println(shiftPacketRelease);
        }

        checkKeys();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for this example
    }

    private void checkKeys() {
        if (altPressed && ctrlPressed && tPressed) {
            // Alt + Ctrl + T is pressed
            System.out.println("Alt + Ctrl + A is pressed");
            // Reset the flags
            altPressed = false;
            ctrlPressed = false;
            tPressed = false;
        }
    }
}