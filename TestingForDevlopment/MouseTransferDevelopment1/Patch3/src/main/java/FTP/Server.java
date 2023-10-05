package FTP;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Server {
	public static void main(String[] args) {
		try {
			JSch jSch = new JSch();
			int port = 2222;

			jSch.addIdentity("D:\\JavaProjects\\.ssh");

			Session session = jSch.getSession("Nayan", null, 8089);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			System.out.println("Server started on port " + port);

			Thread.sleep(Long.MAX_VALUE);

		} catch (JSchException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
