package TAB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Example {

	private static boolean altPressed = false;

	public static void main(String[] args) throws InterruptedException {
		String autoHotkeyPath = "C:\\Program Files\\AutoHotkey\\v2\\AutoHotkey64.exe";

		// Path to the AutoHotkey script
		String scriptPath = "D:\\AltTab.ahk";
		String scriptPath2 = "D:\\JavaProjects\\Wall-less\\TestingForDevlopment\\MouseTransferDevelopment1\\Patch3\\src\\main\\java\\AltTabPreventionExample\\WindowButton.ahk";
		String scriptPath3 = "D:\\JavaProjects\\Wall-less\\TestingForDevlopment\\MouseTransferDevelopment1\\Patch3\\src\\main\\java\\AltTabPreventionExample\\!alt.ahk";


		// Build the command to run the script
		String command = autoHotkeyPath + " " + scriptPath;
		String command3 = autoHotkeyPath + " " + scriptPath3;

		// Start the AutoHotkey script using Runtime.exec
		Process process2 = null;
		try {
			process2 = Runtime.getRuntime().exec(command3);
			Process process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Wait for the AutoHotkey script to finish executing
		Thread.sleep(10000);
//		process.destroy();
		process2.destroy();
	}
}
