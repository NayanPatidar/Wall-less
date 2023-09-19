package Server;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ButtonClicks implements Runnable {

	static private JWindow jWindow;
	private int val = 0;
	private final SharedData sharedData;
	Socket socket;
	DatagramSocket datagramSocket;
	KeyboardFunctionality keyboardFunctionality;
	InetAddress inetAddress;
	int portUDP;

	public ButtonClicks(JWindow jWindow, SharedData sharedData, Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
		ButtonClicks.jWindow = jWindow;
		this.sharedData = sharedData;
		this.socket = socket;
		this.datagramSocket = datagramSocket;
		this.portUDP = portUDP;
		this.inetAddress = inetAddress;
	}

	@Override
	public void run() {
		while (true){
			if ((sharedData.getForButtonClicks() == 0) && (val == 0)){
				System.out.println("Calling Keyboard Functionality");

				SwingUtilities.invokeLater(() -> {
					keyboardFunctionality = new KeyboardFunctionality(jWindow,datagramSocket, inetAddress, portUDP);
				});
				val ++;
			} else if ((sharedData.getForButtonClicks() == 1) && (val == 1)){
				if (keyboardFunctionality != null) {
					System.out.println("Disposing Keyboard Functionality");
					keyboardFunctionality.removeKeyListener();
				}
				jWindow.dispose();
				val --;
			}
		}
	}
}
