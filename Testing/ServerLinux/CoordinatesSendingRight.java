package ServerLinux;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class CoordinatesSendingRight {
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
    int ApparentHeight;
    int ServerHeight;
    int loopNumX = 1;
    int loopNumY = 1;
    String side = "";
    GUI gui = new GUI();


    CoordinatesSendingRight(String side, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP, String clientScreenSize) {
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
        ApparentWidth = ServerWidth - 70;
        ApparentHeight = ServerHeight - 50;
        System.out.println("Server Width : " + ServerWidth + " Server Height : " + ServerHeight);
        if (Objects.equals(side, "Right")) {
            System.out.println("Got at the right position");
            robot.mouseMove(71, First.y);
            gui.setLoopNum(2);
        }

        while (!stop) {
            Point cursorInfo = MouseInfo.getPointerInfo().getLocation();

                String msg = "C:" + gettingXRight(cursorInfo.x, cursorInfo.y) + " " + gettingYRight(cursorInfo.x, cursorInfo.y);
                byte[] sendData = msg.getBytes();

                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, portUDP);
                try {
                    datagramSocket.send(packet);
                    if(gettingXRight(cursorInfo.x, cursorInfo.y) < 1 && Objects.equals(side, "Right")){
                        stop = true;
                        robot.mouseMove(ServerWidth - 3,gettingYRight(cursorInfo.x, cursorInfo.y));
                        gui.setLoopNum(0);
                        break;
                    }
                    Thread.sleep(1);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    public int gettingXRight(int x, int y) {
        int NewX = x-69;
        if (loopNumX == 1) {
            if (NewX >= ApparentWidth) {
                System.out.println("Loop 2");
                loopNumX = 2;
                robot.mouseMove(ApparentWidth - (ClientWidth - ServerWidth), y);
            }
            return NewX;

        } else if (loopNumX == 2) {
            int msg = (x) + (ClientWidth - ServerWidth) ;
            if (x < ApparentWidth - (ClientWidth - ServerWidth)) {
                loopNumX = 1;
                robot.mouseMove(ServerWidth - 2, y);
            }
            return msg;
        }
        return 0;
    }

    public int gettingYRight(int x, int y) {
        int NewY = y-49;
        if (loopNumY == 1) {
            if ((y-50) < 0 ){
                robot.mouseMove(x, 51);
            }
            if (NewY >= ApparentHeight) {
                System.out.println("Loop 2");
                robot.mouseMove(x, ApparentHeight - (ClientHeight - ServerHeight));
                loopNumY = 2;
                System.out.println(ApparentHeight - (ClientHeight - ServerHeight));
            }
            return NewY;
        } else if (loopNumY == 2) {
            if (y < ApparentHeight - (ClientHeight - ServerHeight)) {
                robot.mouseMove(x, ServerHeight - 2);
                loopNumY = 1;
            }
            return y + (ClientHeight - ServerHeight);
        }
        return 0;
    }
}