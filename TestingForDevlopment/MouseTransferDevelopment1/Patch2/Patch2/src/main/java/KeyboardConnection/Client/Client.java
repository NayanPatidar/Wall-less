package KeyboardConnection.Client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, AWTException {

        Robot robot = new Robot();
        Socket socket = new Socket("10.200.233.99", 8080);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        byte[] buffer = new byte[1024];
        while (true) {
            String message = bufferedReader.readLine();
            if (message.startsWith("K:")) {
                String code = message.substring(2);
                System.out.println(code);

                switch (code){
                    case "18":
                        robot.keyPress(18);
                        System.out.println("Alt Pressed");
                        break;
                    case "18'":
                        robot.keyRelease(18);
                        System.out.println("Alt Released");
                        break;
                    case "17":
                        robot.keyPress(17);
                        System.out.println("Ctrl Pressed");
                        break;
                    case "17'":
                        robot.keyRelease(17);
                        System.out.println("Ctrl Released");
                        break;
                    case "65":
                        robot.keyPress(65);
                        System.out.println("A Pressed");
                        break;
                    case "65'":
                        robot.keyRelease(65);
                        System.out.println("A Released");
                        break;
                }
            }
        }
    }
}
