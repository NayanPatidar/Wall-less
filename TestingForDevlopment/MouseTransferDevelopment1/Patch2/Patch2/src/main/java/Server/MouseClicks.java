package Server;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class MouseClicks implements Runnable {

	static private JFrame jFrame;
	private int val = 0;
	private final SharedData sharedData;
	Socket socket;
	DatagramSocket datagramSocket;
	MouseFunctionality mouseFunctionality;
	InetAddress inetAddress;
	int portUDP;

	public MouseClicks(JFrame jFrame, SharedData sharedData, Socket socket, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
		MouseClicks.jFrame = jFrame;
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
				System.out.println("Calling Mouse Functionality");

				SwingUtilities.invokeLater(() -> {
					mouseFunctionality = new MouseFunctionality(jFrame,datagramSocket, inetAddress, portUDP);
				});
				val ++;
			} else if ((sharedData.getForMouseClicks() == 1) && (val == 1)){
				System.out.println("Disposing Mouse Functionality");
				if (mouseFunctionality != null) {
					mouseFunctionality.disposeMouseListener();
				}
				jFrame.dispose();
				val --;
			}
		}
	}
}
