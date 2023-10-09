package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

public class ReceivingCoordinates implements Runnable {

	DatagramSocket datagramSocket;
	InetAddress inetAddress;
	Socket clientSocket;
	String side;

	private JFrame dummyFrame;
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	Robot robot;

	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}


	public ReceivingCoordinates(String side, DatagramSocket datagramSocket, InetAddress inetAddress, Socket clientSocket) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
		this.clientSocket = clientSocket;
		this.side = side;

		// Create a dummy invisible frame
		dummyFrame = new JFrame();
		dummyFrame.setUndecorated(true);
		dummyFrame.setSize(1, 1);
		dummyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dummyFrame.setAlwaysOnTop(true);

		dummyFrame.setVisible(true);

	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize().getSize();
					case "45": // Minus (-) key
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						System.out.println("Pressed Minus (-) key");
						break;
					case "35": // End key
						robot.keyPress(KeyEvent.VK_END);
						robot.keyRelease(KeyEvent.VK_END);
						System.out.println("Pressed End key");
						break;
					case "34": // Page Down key
						robot.keyPress(KeyEvent.VK_PAGE_DOWN);
						robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
						System.out.println("Pressed Page Down key");
						break;
					case "33": // Page Up key
						robot.keyPress(KeyEvent.VK_PAGE_UP);
						robot.keyRelease(KeyEvent.VK_PAGE_UP);
						System.out.println("Pressed Page Up key");
						break;
					case "144": // Num Lock key
						robot.keyPress(KeyEvent.VK_NUM_LOCK);
						robot.keyRelease(KeyEvent.VK_NUM_LOCK);
						System.out.println("Pressed Num Lock key");
						break;
					case "36": // Home key
						robot.keyPress(KeyEvent.VK_HOME);
						robot.keyRelease(KeyEvent.VK_HOME);
						System.out.println("Pressed Home key");
						break;
					case "192": // Backtick/Grave Accent key
						System.out.println("Backtick/Grave Accent Key");
						robot.keyPress(KeyEvent.VK_BACK_QUOTE);
						robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
						break;
				}
			}
			else if (receivedMsg.startsWith("M:")) {
					System.out.println(receivedMsg);
					String msg = receivedMsg.substring(2);

					switch (msg){
						case "1":
							robot.mouseWheel(1);
							break;
						case "-1":
							robot.mouseWheel(-1);
							break;
					}
				}
			}
		}
	}

