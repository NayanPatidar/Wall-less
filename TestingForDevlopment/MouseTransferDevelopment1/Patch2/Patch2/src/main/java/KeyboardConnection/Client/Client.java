package KeyboardConnection.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("10.200.233.99", 8080);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        byte[] buffer = new byte[1024];
        String message = bufferedReader.readLine();
        if (message.startsWith("K:")) {
            String code = message.substring(2);
            System.out.println(code);
        }

    }
}
