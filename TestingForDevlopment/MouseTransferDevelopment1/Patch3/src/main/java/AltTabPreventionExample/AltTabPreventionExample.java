package AltTabPreventionExample;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AltTabPreventionExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Path to the AutoHotkey executable
//		C:\Program Files\AutoHotkey\v2
		String autoHotkeyPath = "C:\\Program Files\\AutoHotkey\\v2\\AutoHotkey64.exe";

		// Path to the AutoHotkey script
		String scriptPath = "D:\\AltTab.ahk";

		// Build the command to run the script
		String command = autoHotkeyPath + " " + scriptPath;

		// Start the AutoHotkey script using Runtime.exec
		Process process = Runtime.getRuntime().exec(command);

		// Wait for the AutoHotkey script to finish executing
		Thread.sleep(10000);

//		String scriptPath2 = "D:\\AllowAltTab.ahk";
//
//		String command2 = autoHotkeyPath + " " + scriptPath2;
//
//		// Start the AutoHotkey script using Runtime.exec
//		Process process2 = Runtime.getRuntime().exec(command2);

	}
}
