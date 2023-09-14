package Server;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class KeyboardFunctionality  {
	static private JFrame jFrame;
	public static KeyListener keyListener;

	public KeyboardFunctionality(JFrame jFrame, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {

		KeyboardFunctionality.jFrame = jFrame;

		jFrame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				System.out.println(keyChar);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println("Key Pressed has the code : " + keyCode);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println("Key Released has the code : " + keyCode);
			}
		});
		jFrame.addKeyListener(keyListener);
	}


}
