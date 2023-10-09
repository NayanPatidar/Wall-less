package LinuxJframe;

import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.interfaces.DBusInterface;

public class Main {

    public static void main(String[] args) {
        try {
            // Connect to D-Bus
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DEFAULT_SYSTEM_BUS_ADDRESS);

            // Query GNOME Shell for taskbar size and position
            Object gnomeShell = connection.getRemoteObject("org.gnome.Shell", "/org/gnome/Shell");
            DBusInterface gnomeShellInterface = (DBusInterface) gnomeShell;

            int taskbarHeight = (int) gnomeShellInterface.getProperty("panelHeight");
            int taskbarPosition = (int) gnomeShellInterface.getProperty("panelPosition");

            System.out.println("Taskbar Height: " + taskbarHeight);
            System.out.println("Taskbar Position (1: Top, 2: Bottom, 3: Left, 4: Right): " + taskbarPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
