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
		String scriptPath2 = "D:\\JavaProjects\\Wall-less\\TestingForDevlopment\\MouseTransferDevelopment1\\Patch3\\src\\main\\java\\AltTabPreventionExample\\WindowButton.ahk";

		// Build the command to run the script
		String command = autoHotkeyPath + " " + scriptPath;
		String command2 = autoHotkeyPath + " " + scriptPath2;

		// Start the AutoHotkey script using Runtime.exec
		Process process = Runtime.getRuntime().exec(command);
		Process process2 = Runtime.getRuntime().exec(command2);

		// Wait for the AutoHotkey script to finish executing
		Thread.sleep(10000);
//		process.destroy();
		process2.destroy();
//		String scriptPath2 = "D:\\AllowAltTab.ahk";
//
//		String command2 = autoHotkeyPath + " " + scriptPath2;
//
//		// Start the AutoHotkey script using Runtime.exec
//		Process process2 = Runtime.getRuntime().exec(command2);

	}
}
