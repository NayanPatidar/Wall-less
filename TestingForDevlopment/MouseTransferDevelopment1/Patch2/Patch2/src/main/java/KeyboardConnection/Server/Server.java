package KeyboardConnection.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400,400);

        MyKeyListener keyListener = new MyKeyListener(socket);
        jFrame.addKeyListener(keyListener);

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jFrame.requestFocus();
        jFrame.setVisible(true);

    }
}