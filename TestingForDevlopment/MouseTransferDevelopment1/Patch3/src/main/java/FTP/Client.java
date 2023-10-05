package FTP;

import com.jcraft.jsch.JSch;

public class Client {
	public static void main(String[] args) {
		try {
			JSch jSch = new JSch();

			jSch.addIdentity();
		}
	}
}
