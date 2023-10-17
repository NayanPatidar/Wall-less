package ServerLinux;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class CoordinatesSendingLeft {
    Robot robot;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    DatagramSocket datagramSocket;
    InetAddress inetAddress;
    int portUDP;
    boolean stop = false;
    String clientScreenSize = " ";
    int ClientWidth;
    int ClientHeight;
    int ServerWidth;
    int ApparentWidth;
    int ServerHeight;
    int loopNumX = 1;
    int loopNumY = 1;
    String side = "";
    GUI gui = new GUI();

    CoordinatesSendingLeft(String side, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP, String clientScreenSize) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
        this.portUDP = portUDP;
        this.clientScreenSize = clientScreenSize;
        this.side = side;

        sendingCoordinates();
    }


    public void sendingCoordinates() {

        String[] clientScreen = clientScreenSize.split(" ");
        ClientHeight = Integer.parseInt(clientScreen[0]);
        ClientWidth = Integer.parseInt(clientScreen[1]);
        System.out.println("Starting to send the position is running !!");

        Point First = MouseInfo.getPointerInfo().getLocation();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        ServerHeight = dimension.height;
        ServerWidth = dimension.width;
        ApparentWidth = ServerWidth-70;
        System.out.println("Server Width : " + ServerWidth + " Server Height : " + ServerHeight);
        if (Objects.equals(side, "Left")) {
            robot.mouseMove(dimension.width - 3, First.y);
            gui.setLoopNum(1);
            System.out.println("Loop set to 1");
        }

        while (!stop) {
            Point cursorInfo = MouseInfo.getPointerInfo().getLocation();

                String msg = "C:" + gettingXLeft(cursorInfo.x, cursorInfo.y) + " " +  gettingYLeft(cursorInfo.x, cursorInfo.y);
                byte[] sendData = msg.getBytes();

                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
                try {
                    datagramSocket.send(packet);
                    if (gettingXLeft(cursorInfo.x, cursorInfo.y) > ClientWidth - 2 && Objects.equals(side, "Left")) {
                        stop = true;
                        robot.mouseMove(2, gettingYLeft(cursorInfo.x, cursorInfo.y));
                        gui.setLoopNum(0);
                        System.out.println("Broke");
                        break;
                    }
                    Thread.sleep(2);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public int gettingXLeft(int x, int y) {
        if (loopNumX == 1) {
            int msg = x - (ServerWidth - ClientWidth);

            // This will anyway not be reached if the server is smaller than client
            if (msg < -1) {
                robot.mouseMove((ServerWidth - ClientWidth) + 1, y);
            }
            // As my Linux is bigger than the Windows
//            System.out.println(msg);
			if (x <= 0){
				loopNumX = 2;
				System.out.println("Loop 2 Initialized");
				robot.mouseMove(ClientWidth-ServerWidth, y);
			}
            return msg;
        }
        else if (loopNumX == 2) {
            if (x > (ClientWidth-ServerWidth)) {
                loopNumX = 1;
                robot.mouseMove(1, y);
            }
            return x;
        }
        return 0;
    }

    public int gettingYLeft(int x, int y) {
        if (loopNumY == 1) {
            int msg = y - (ServerHeight - ClientHeight);
            if (0 > msg) {
                robot.mouseMove(x, (ServerHeight - ClientHeight) + 1);
            }
            if (y == 0){
                robot.mouseMove(x, (ClientHeight - ServerHeight));
                loopNumY = 2;
            }
            return msg;

        } else if (loopNumY == 2) {
            if (y > (ClientWidth - ServerWidth)) {
                robot.mouseMove(x, 1 + (ClientHeight - ServerHeight));
                loopNumY = 1;
            }
            return y ;
        }
        return 0;
    }
}