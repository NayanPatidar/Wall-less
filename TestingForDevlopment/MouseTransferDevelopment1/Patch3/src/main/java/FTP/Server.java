package FTP;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Server {
	public static void main(String[] args) {
		try {
			JSch jSch = new JSch();
			int port = 8089;

			jSch.addIdentity("D:\\JavaProjects\\id_rsa");

			Session session = jSch.getSession("Nayan", "10.200.233.107", port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			System.out.println("Server started on port " + port);

			Thread.sleep(Long.MAX_VALUE);

		} catch (JSchException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
