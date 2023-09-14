package Server;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ButtonClicks implements Runnable {
	static private JFrame jFrame;
	private int val = 0;
	private final SharedData sharedData;
	Socket socket;
	DatagramSocket datagramSocket;
	KeyboardFunctionality keyboardFunctionality;
	InetAddress inetAddress;
	int portUDP;

	public ButtonClicks(JFrame jFrame, SharedData sharedData, Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
		ButtonClicks.jFrame = jFrame;
		this.sharedData = sharedData;
		this.socket = socket;
		this.datagramSocket = datagramSocket;
		this.portUDP = portUDP;
		this.inetAddress = inetAddress;
	}

	@Override
	public void run() {
		while (true){
			if ((sharedData.getForMouseClicks() == 0) && (val == 0)){
				System.out.println("Calling Keyboard Functionality");

				SwingUtilities.invokeLater(() -> {
					keyboardFunctionality = new KeyboardFunctionality(jFrame,datagramSocket, inetAddress, portUDP);
				});
				val ++;
			} else if ((sharedData.getForMouseClicks() == 1) && (val == 1)){
				System.out.println("Disposing Keyboard Functionality");
				if (keyboardFunctionality != null) {
//					keyboardFunctionality.();
				}
				jFrame.dispose();
				val --;
			}
		}
	}
}
