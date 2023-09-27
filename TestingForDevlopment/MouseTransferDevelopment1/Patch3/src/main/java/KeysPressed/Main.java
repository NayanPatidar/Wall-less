package KeysPressed;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		// Creating a JFrame
		JFrame jFrame = new JFrame();
		final Toolkit toolkit = Toolkit.getDefaultToolkit();

		jFrame.setSize(50,50);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setAlwaysOnTop(true);
		jFrame.setBackground(new Color(0,0,0,30));
		jFrame.setType(Window.Type.UTILITY);

		System.out.println("Starting");
		Thread thread = new Thread(new KeysTesting(jFrame));
		Thread.sleep(10000);
		jFrame.setVisible(false);
		System.out.println("Ended");
		TimeUnit.SECONDS.sleep(10);
	}
}
