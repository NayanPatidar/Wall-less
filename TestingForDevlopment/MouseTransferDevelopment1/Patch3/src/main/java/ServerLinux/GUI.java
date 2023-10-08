package ServerLinux;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class GUI {
	DatagramSocket datagramSocket;
	JFrame jFrame;
	JTextField jTextField;
	InetAddress inetAddress;
	int portUDP ;
	String clientScreenSize;
	EventListener eventListener;
	String side;
	String OS;
	int val = 0;

	public GUI(String OS, String side, JFrame jFrame, JTextField jTextField, InetAddress inetAddress, DatagramSocket datagramSocket, int portUDP, String clientScreenSize) {
		this.jFrame = jFrame;
		this.jTextField = jTextField;
		this.inetAddress = inetAddress;
		this.datagramSocket = datagramSocket;
		this.portUDP = portUDP;
		this.clientScreenSize = clientScreenSize;
		this.side = side;
		this.OS = OS;
		Start();
	}


	public void Start() {
		System.out.println("Screen Sharing started !!");
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		while (true){
			
		}
	}
}
