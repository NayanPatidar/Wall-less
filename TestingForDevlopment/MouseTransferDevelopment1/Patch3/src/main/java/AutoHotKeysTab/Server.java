package AutoHotKeysTab;

import java.io.IOException;

public class Server {
	public static void main(String[] args) {
		String autoHotkeyPath = "C:\\Program Files\\AutoHotkey\\v2\\AutoHotkey64.exe";
		String scriptPath = "D:\\JavaProjects\\Wall-less\\TestingForDevlopment\\MouseTransferDevelopment1\\Patch3\\src\\main\\java\\AutoHotKeysTab\\TabPacket.ahk";
		String command = autoHotkeyPath + " " + scriptPath;

		try {
			Process process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
