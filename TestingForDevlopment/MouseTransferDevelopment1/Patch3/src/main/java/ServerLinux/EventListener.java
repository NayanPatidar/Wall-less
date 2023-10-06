package ServerLinux;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EventListener {
    static private JWindow jWindow;
    private  JFrame jFrame;
    private static boolean tabPressed = false;
    private  MouseAdapter mouseAdapter;
    private  KeyListener keyListener;
    private  MouseWheelListener mouseWheelListener;
    boolean altPress = false;
    boolean ctrlPress = false;
    boolean shiftPress = false;


    public EventListener(JFrame frame, DatagramSocket datagramSocket, InetAddress inetAddress, int portUDP) {
        jFrame = frame;
        String leftClickPressed = "B:3";
        String middleClickPressed = "B:2";
        String rightClickPressed = "B:1";
        byte[] bufferLeftPressed = leftClickPressed.getBytes();
        byte[] bufferRightPressed = rightClickPressed.getBytes();
        byte[] bufferMiddlePressed = middleClickPressed.getBytes();
        String leftClickReleased = "B:3'";
        String middleClickReleased = "B:2'";
        String rightClickReleased = "B:1'";
        byte[] bufferLeftReleased = leftClickReleased.getBytes();
        byte[] bufferRightReleased = rightClickReleased.getBytes();
        byte[] bufferMiddleReleased = middleClickReleased.getBytes();
        DatagramPacket packetLeftReleased = new DatagramPacket(bufferLeftReleased, bufferLeftReleased.length, inetAddress, portUDP);
        DatagramPacket packetMiddleReleased = new DatagramPacket(bufferMiddleReleased, bufferMiddleReleased.length, inetAddress, portUDP);
        DatagramPacket packetRightReleased = new DatagramPacket(bufferRightReleased, bufferRightReleased.length, inetAddress, portUDP);
        DatagramPacket packetLeftPressed = new DatagramPacket(bufferLeftPressed, bufferLeftPressed.length, inetAddress, portUDP);
        DatagramPacket packetMiddlePressed = new DatagramPacket(bufferMiddlePressed, bufferMiddlePressed.length, inetAddress, portUDP);
        DatagramPacket packetRightPressed = new DatagramPacket(bufferRightPressed, bufferRightPressed.length, inetAddress, portUDP);

        String spaceKey = "K:32";
        String enterKey = "K:10";
        String tabKey = "K:9";
        String shiftKey = "K:16";
        String ctrlKey = "K:17";
        String altKey = "K:18";
        String escKey = "K:27";
        String backspaceKey = "K:8";
        String capsLockKey = "K:20";
        String f1Key = "K:112";
        String f2Key = "K:113";
        String f3Key = "K:114";
        String f4Key = "K:115";
        String f5Key = "K:116";
        String f6Key = "K:117";
        String f7Key = "K:118";
        String f8Key = "K:119";
        String f9Key = "K:120";
        String f10Key = "K:121";
        String f11Key = "K:122";
        String f12Key = "K:123";
        String num0Key = "K:48";
        String num1Key = "K:49";
        String num2Key = "K:50";
        String num3Key = "K:51";
        String num4Key = "K:52";
        String num5Key = "K:53";
        String num6Key = "K:54";
        String num7Key = "K:55";
        String num8Key = "K:56";
        String num9Key = "K:57";
        String aKey = "K:65";
        String bKey = "K:66";
        String cKey = "K:67";
        String dKey = "K:68";
        String eKey = "K:69";
        String fKey = "K:70";
        String gKey = "K:71";
        String hKey = "K:72";
        String iKey = "K:73";
        String jKey = "K:74";
        String kKey = "K:75";
        String lKey = "K:76";
        String mKey = "K:77";
        String nKey = "K:78";
        String oKey = "K:79";
        String pKey = "K:80";
        String qKey = "K:81";
        String rKey = "K:82";
        String sKey = "K:83";
        String tKey = "K:84";
        String uKey = "K:85";
        String vKey = "K:86";
        String wKey = "K:87";
        String xKey = "K:88";
        String yKey = "K:89";
        String zKey = "K:90";
        String deleteKey = "K:46";
        String equalKey = "K:61";
        String minusKey = "K:45";
        String endKey = "K:35";
        String pageDownKey = "K:34";
        String numLockKey = "K:144";
        String homeKey = "K:36";
        String backtickKey = "K:192";
        String pageUpKey = "K:33";
        String quoteKey = "K:222"; // Quote key (')
        String semicolonKey = "K:59";
        String periodKey = "K:46"; // Period key (.)
        String commaKey = "K:44"; // Comma key (,)
        String arrowUpKey = "K:38"; // Arrow Up key
        String arrowDownKey = "K:40"; // Arrow Down key
        String closeBracketKey = "K:93"; // Close Bracket key (])
        String openBracketKey = "K:91"; // Open Bracket key ([)
        String arrowLeftKey = "K:37"; // Arrow Left key
        String arrowRightKey = "K:39"; // Arrow Right key
        String backslashKey = "K:92"; // Backslash key (\)
        String forwardSlashKey = "K:47"; // Forward Slash key (/)

        String spaceKey_released = "K:32'";
        String enterKey_released = "K:10'";
        String tabKey_released = "K:9'";
        String shiftKey_released = "K:16'";
        String ctrlKey_released = "K:17'";
        String altKey_released = "K:18'";
        String escKey_released = "K:27'";
        String backspaceKey_released = "K:8'";
        String capsLockKey_released = "K:20'";
        String f1Key_released = "K:112'";
        String f2Key_released = "K:113'";
        String f3Key_released = "K:114'";
        String f4Key_released = "K:115'";
        String f5Key_released = "K:116'";
        String f6Key_released = "K:117'";
        String f7Key_released = "K:118'";
        String f8Key_released = "K:119'";
        String f9Key_released = "K:120'";
        String f10Key_released = "K:121'";
        String f11Key_released = "K:122'";
        String f12Key_released = "K:123'";
        String num0Key_released = "K:48'";
        String num1Key_released = "K:49'";
        String num2Key_released = "K:50'";
        String num3Key_released = "K:51'";
        String num4Key_released = "K:52'";
        String num5Key_released = "K:53'";
        String num6Key_released = "K:54'";
        String num7Key_released = "K:55'";
        String num8Key_released = "K:56'";
        String num9Key_released = "K:57'";
        String aKey_released = "K:65'";
        String bKey_released = "K:66'";
        String cKey_released = "K:67'";
        String dKey_released = "K:68'";
        String eKey_released = "K:69'";
        String fKey_released = "K:70'";
        String gKey_released = "K:71'";
        String hKey_released = "K:72'";
        String iKey_released = "K:73'";
        String jKey_released = "K:74'";
        String kKey_released = "K:75'";
        String lKey_released = "K:76'";
        String mKey_released = "K:77'";
        String nKey_released = "K:78'";
        String oKey_released = "K:79'";
        String pKey_released = "K:80'";
        String qKey_released = "K:81'";
        String rKey_released = "K:82'";
        String sKey_released = "K:83'";
        String tKey_released = "K:84'";
        String uKey_released = "K:85'";
        String vKey_released = "K:86'";
        String wKey_released = "K:87'";
        String xKey_released = "K:88'";
        String yKey_released = "K:89'";
        String zKey_released = "K:90'";
        String deleteKey_released = "K:46'";
        String equalKey_released = "K:61'";
        String minusKey_released = "K:45'";
        String endKey_released = "K:35'";
        String pageDownKey_released = "K:34'";
        String numLockKey_released = "K:144'";
        String homeKey_released = "K:36'";
        String backtickKey_released = "K:192'";
        String pageUpKey_released = "K:33'";
        String quoteKey_released = "K:222'"; // Quote key (')
        String semicolonKey_released = "K:59'";
        String periodKey_released = "K:46'"; // Period key (.)
        String commaKey_released = "K:44'"; // Comma key (,)
        String arrowUpKey_released = "K:38'"; // Arrow Up key
        String arrowDownKey_released = "K:40'"; // Arrow Down key
        String closeBracketKey_released = "K:93'"; // Close Bracket key (])
        String openBracketKey_released = "K:91'"; // Open Bracket key ([)
        String arrowLeftKey_released = "K:37'"; // Arrow Left key
        String arrowRightKey_released = "K:39'"; // Arrow Right key
        String backslashKey_released = "K:92'"; // Backslash key (\)
        String forwardSlashKey_released = "K:47'"; // Forward Slash key (/)
        String mouseScrollDown = "M:1";
        String mouseScrollUp = "M:-1";

        byte[] scrollBytesDown = mouseScrollDown.getBytes();
        byte[] scrollBytesUp = mouseScrollUp.getBytes();
        byte[] pageUpKeyBytes = pageUpKey.getBytes();
        byte[] backtickKeyBytes = backtickKey.getBytes();
        byte[] deleteKey_pressed = deleteKey.getBytes();
        byte[] spaceKey_pressed = spaceKey.getBytes();
        byte[] enterKey_pressed = enterKey.getBytes();
        byte[] tabKey_pressed = tabKey.getBytes();
        byte[] shiftKey_pressed = shiftKey.getBytes();
        byte[] ctrlKey_pressed = ctrlKey.getBytes();
        byte[] altKey_pressed = altKey.getBytes();
        byte[] escKey_pressed = escKey.getBytes();
        byte[] backspaceKey_pressed = backspaceKey.getBytes();
        byte[] capsLockKey_pressed = capsLockKey.getBytes();
        byte[] f1Key_pressed = f1Key.getBytes();
        byte[] f2Key_pressed = f2Key.getBytes();
        byte[] f3Key_pressed = f3Key.getBytes();
        byte[] f4Key_pressed = f4Key.getBytes();
        byte[] f5Key_pressed = f5Key.getBytes();
        byte[] f6Key_pressed = f6Key.getBytes();
        byte[] f7Key_pressed = f7Key.getBytes();
        byte[] f8Key_pressed = f8Key.getBytes();
        byte[] f9Key_pressed = f9Key.getBytes();
        byte[] f10Key_pressed = f10Key.getBytes();
        byte[] f11Key_pressed = f11Key.getBytes();
        byte[] f12Key_pressed = f12Key.getBytes();
        byte[] num0Key_pressed = num0Key.getBytes();
        byte[] num1Key_pressed = num1Key.getBytes();
        byte[] num2Key_pressed = num2Key.getBytes();
        byte[] num3Key_pressed = num3Key.getBytes();
        byte[] num4Key_pressed = num4Key.getBytes();
        byte[] num5Key_pressed = num5Key.getBytes();
        byte[] num6Key_pressed = num6Key.getBytes();
        byte[] num7Key_pressed = num7Key.getBytes();
        byte[] num8Key_pressed = num8Key.getBytes();
        byte[] num9Key_pressed = num9Key.getBytes();
        byte[] aKey_pressed = aKey.getBytes();
        byte[] bKey_pressed = bKey.getBytes();
        byte[] cKey_pressed = cKey.getBytes();
        byte[] dKey_pressed = dKey.getBytes();
        byte[] eKey_pressed = eKey.getBytes();
        byte[] fKey_pressed = fKey.getBytes();
        byte[] gKey_pressed = gKey.getBytes();
        byte[] hKey_pressed = hKey.getBytes();
        byte[] iKey_pressed = iKey.getBytes();
        byte[] jKey_pressed = jKey.getBytes();
        byte[] kKey_pressed = kKey.getBytes();
        byte[] lKey_pressed = lKey.getBytes();
        byte[] mKey_pressed = mKey.getBytes();
        byte[] nKey_pressed = nKey.getBytes();
        byte[] oKey_pressed = oKey.getBytes();
        byte[] pKey_pressed = pKey.getBytes();
        byte[] qKey_pressed = qKey.getBytes();
        byte[] rKey_pressed = rKey.getBytes();
        byte[] sKey_pressed = sKey.getBytes();
        byte[] tKey_pressed = tKey.getBytes();
        byte[] uKey_pressed = uKey.getBytes();
        byte[] vKey_pressed = vKey.getBytes();
        byte[] wKey_pressed = wKey.getBytes();
        byte[] xKey_pressed = xKey.getBytes();
        byte[] yKey_pressed = yKey.getBytes();
        byte[] zKey_pressed = zKey.getBytes();
        byte[] equalKeyBytes = equalKey.getBytes();
        byte[] minusKeyBytes = minusKey.getBytes();
        byte[] endKeyBytes = endKey.getBytes();
        byte[] pageDownKeyBytes = pageDownKey.getBytes();
        byte[] numLockKeyBytes = numLockKey.getBytes();
        byte[] homeKeyBytes = homeKey.getBytes();

        byte[] spaceKey_released_bytes = spaceKey_released.getBytes();
        byte[] enterKey_released_bytes = enterKey_released.getBytes();
        byte[] tabKey_released_bytes = tabKey_released.getBytes();
        byte[] shiftKey_released_bytes = shiftKey_released.getBytes();
        byte[] ctrlKey_released_bytes = ctrlKey_released.getBytes();
        byte[] altKey_released_bytes = altKey_released.getBytes();
        byte[] escKey_released_bytes = escKey_released.getBytes();
        byte[] backspaceKey_released_bytes = backspaceKey_released.getBytes();
        byte[] capsLockKey_released_bytes = capsLockKey_released.getBytes();
        byte[] f1Key_released_bytes = f1Key_released.getBytes();
        byte[] f2Key_released_bytes = f2Key_released.getBytes();
        byte[] f3Key_released_bytes = f3Key_released.getBytes();
        byte[] f4Key_released_bytes = f4Key_released.getBytes();
        byte[] f5Key_released_bytes = f5Key_released.getBytes();
        byte[] f6Key_released_bytes = f6Key_released.getBytes();
        byte[] f7Key_released_bytes = f7Key_released.getBytes();
        byte[] f8Key_released_bytes = f8Key_released.getBytes();
        byte[] f9Key_released_bytes = f9Key_released.getBytes();
        byte[] f10Key_released_bytes = f10Key_released.getBytes();
        byte[] f11Key_released_bytes = f11Key_released.getBytes();
        byte[] f12Key_released_bytes = f12Key_released.getBytes();
        byte[] num0Key_released_bytes = num0Key_released.getBytes();
        byte[] num1Key_released_bytes = num1Key_released.getBytes();
        byte[] num2Key_released_bytes = num2Key_released.getBytes();
        byte[] num3Key_released_bytes = num3Key_released.getBytes();
        byte[] num4Key_released_bytes = num4Key_released.getBytes();
        byte[] num5Key_released_bytes = num5Key_released.getBytes();
        byte[] num6Key_released_bytes = num6Key_released.getBytes();
        byte[] num7Key_released_bytes = num7Key_released.getBytes();
        byte[] num8Key_released_bytes = num8Key_released.getBytes();
        byte[] num9Key_released_bytes = num9Key_released.getBytes();
        byte[] aKey_released_bytes = aKey_released.getBytes();
        byte[] bKey_released_bytes = bKey_released.getBytes();
        byte[] cKey_released_bytes = cKey_released.getBytes();
        byte[] dKey_released_bytes = dKey_released.getBytes();
        byte[] eKey_released_bytes = eKey_released.getBytes();
        byte[] fKey_released_bytes = fKey_released.getBytes();
        byte[] gKey_released_bytes = gKey_released.getBytes();
        byte[] hKey_released_bytes = hKey_released.getBytes();
        byte[] iKey_released_bytes = iKey_released.getBytes();
        byte[] jKey_released_bytes = jKey_released.getBytes();
        byte[] kKey_released_bytes = kKey_released.getBytes();
        byte[] lKey_released_bytes = lKey_released.getBytes();
        byte[] mKey_released_bytes = mKey_released.getBytes();
        byte[] nKey_released_bytes = nKey_released.getBytes();
        byte[] oKey_released_bytes = oKey_released.getBytes();
        byte[] pKey_released_bytes = pKey_released.getBytes();
        byte[] qKey_released_bytes = qKey_released.getBytes();
        byte[] rKey_released_bytes = rKey_released.getBytes();
        byte[] sKey_released_bytes = sKey_released.getBytes();
        byte[] tKey_released_bytes = tKey_released.getBytes();
        byte[] uKey_released_bytes = uKey_released.getBytes();
        byte[] vKey_released_bytes = vKey_released.getBytes();
        byte[] wKey_released_bytes = wKey_released.getBytes();
        byte[] xKey_released_bytes = xKey_released.getBytes();
        byte[] yKey_released_bytes = yKey_released.getBytes();
        byte[] zKey_released_bytes = zKey_released.getBytes();
        byte[] deleteKey_released_bytes = deleteKey_released.getBytes();
        byte[] equalKey_released_bytes = equalKey_released.getBytes();
        byte[] minusKey_released_bytes = minusKey_released.getBytes();
        byte[] endKey_released_bytes = endKey_released.getBytes();
        byte[] pageDownKey_released_bytes = pageDownKey_released.getBytes();
        byte[] numLockKey_released_bytes = numLockKey_released.getBytes();
        byte[] homeKey_released_bytes = homeKey_released.getBytes();
        byte[] backtickKey_released_bytes = backtickKey_released.getBytes();
        byte[] pageUpKey_released_bytes = pageUpKey_released.getBytes();
        byte[] quoteKey_released_bytes = quoteKey_released.getBytes();
        byte[] semicolonKey_released_bytes = semicolonKey_released.getBytes();
        byte[] periodKey_released_bytes = periodKey_released.getBytes();
        byte[] commaKey_released_bytes = commaKey_released.getBytes();
        byte[] arrowUpKey_released_bytes = arrowUpKey_released.getBytes();
        byte[] arrowDownKey_released_bytes = arrowDownKey_released.getBytes();
        byte[] closeBracketKey_released_bytes = closeBracketKey_released.getBytes();
        byte[] openBracketKey_released_bytes = openBracketKey_released.getBytes();
        byte[] arrowLeftKey_released_bytes = arrowLeftKey_released.getBytes();
        byte[] arrowRightKey_released_bytes = arrowRightKey_released.getBytes();
        byte[] backslashKey_released_bytes = backslashKey_released.getBytes();
        byte[] forwardSlashKey_released_bytes = forwardSlashKey_released.getBytes();

        DatagramPacket packet_mouseScrollDown = new DatagramPacket(scrollBytesDown, scrollBytesDown.length, inetAddress, portUDP);
        DatagramPacket packet_mouseScrollUp = new DatagramPacket(scrollBytesUp, scrollBytesUp.length, inetAddress, portUDP);
        DatagramPacket packet_deleteKey_pressed = new DatagramPacket(deleteKey_pressed, deleteKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_spaceKey_pressed = new DatagramPacket(spaceKey_pressed, spaceKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_enterKey_pressed = new DatagramPacket(enterKey_pressed, enterKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_tabKey_pressed = new DatagramPacket(tabKey_pressed, tabKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_shiftKey_pressed = new DatagramPacket(shiftKey_pressed, shiftKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_ctrlKey_pressed = new DatagramPacket(ctrlKey_pressed, ctrlKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_altKey_pressed = new DatagramPacket(altKey_pressed, altKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_escKey_pressed = new DatagramPacket(escKey_pressed, escKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_backspaceKey_pressed = new DatagramPacket(backspaceKey_pressed, backspaceKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_capsLockKey_pressed = new DatagramPacket(capsLockKey_pressed, capsLockKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f1Key_pressed = new DatagramPacket(f1Key_pressed, f1Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f2Key_pressed = new DatagramPacket(f2Key_pressed, f2Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f3Key_pressed = new DatagramPacket(f3Key_pressed, f3Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f4Key_pressed = new DatagramPacket(f4Key_pressed, f4Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f5Key_pressed = new DatagramPacket(f5Key_pressed, f5Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f6Key_pressed = new DatagramPacket(f6Key_pressed, f6Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f7Key_pressed = new DatagramPacket(f7Key_pressed, f7Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f8Key_pressed = new DatagramPacket(f8Key_pressed, f8Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f9Key_pressed = new DatagramPacket(f9Key_pressed, f9Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f10Key_pressed = new DatagramPacket(f10Key_pressed, f10Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f11Key_pressed = new DatagramPacket(f11Key_pressed, f11Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_f12Key_pressed = new DatagramPacket(f12Key_pressed, f12Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num0Key_pressed = new DatagramPacket(num0Key_pressed, num0Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num1Key_pressed = new DatagramPacket(num1Key_pressed, num1Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num2Key_pressed = new DatagramPacket(num2Key_pressed, num2Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num3Key_pressed = new DatagramPacket(num3Key_pressed, num3Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num4Key_pressed = new DatagramPacket(num4Key_pressed, num4Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num5Key_pressed = new DatagramPacket(num5Key_pressed, num5Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num6Key_pressed = new DatagramPacket(num6Key_pressed, num6Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num7Key_pressed = new DatagramPacket(num7Key_pressed, num7Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num8Key_pressed = new DatagramPacket(num8Key_pressed, num8Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_num9Key_pressed = new DatagramPacket(num9Key_pressed, num9Key_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_aKey_pressed = new DatagramPacket(aKey_pressed, aKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_bKey_pressed = new DatagramPacket(bKey_pressed, bKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_cKey_pressed = new DatagramPacket(cKey_pressed, cKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_dKey_pressed = new DatagramPacket(dKey_pressed, dKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_eKey_pressed = new DatagramPacket(eKey_pressed, eKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_fKey_pressed = new DatagramPacket(fKey_pressed, fKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_gKey_pressed = new DatagramPacket(gKey_pressed, gKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_hKey_pressed = new DatagramPacket(hKey_pressed, hKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_iKey_pressed = new DatagramPacket(iKey_pressed, iKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_jKey_pressed = new DatagramPacket(jKey_pressed, jKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_kKey_pressed = new DatagramPacket(kKey_pressed, kKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_lKey_pressed = new DatagramPacket(lKey_pressed, lKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_mKey_pressed = new DatagramPacket(mKey_pressed, mKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_nKey_pressed = new DatagramPacket(nKey_pressed, nKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_oKey_pressed = new DatagramPacket(oKey_pressed, oKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_pKey_pressed = new DatagramPacket(pKey_pressed, pKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_qKey_pressed = new DatagramPacket(qKey_pressed, qKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_rKey_pressed = new DatagramPacket(rKey_pressed, rKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_sKey_pressed = new DatagramPacket(sKey_pressed, sKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_tKey_pressed = new DatagramPacket(tKey_pressed, tKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_uKey_pressed = new DatagramPacket(uKey_pressed, uKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_vKey_pressed = new DatagramPacket(vKey_pressed, vKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_wKey_pressed = new DatagramPacket(wKey_pressed, wKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_xKey_pressed = new DatagramPacket(xKey_pressed, xKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_yKey_pressed = new DatagramPacket(yKey_pressed, yKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packet_zKey_pressed = new DatagramPacket(zKey_pressed, zKey_pressed.length, inetAddress, portUDP);
        DatagramPacket packetEqualKey = new DatagramPacket(equalKeyBytes, equalKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetMinusKey = new DatagramPacket(minusKeyBytes, minusKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetEndKey = new DatagramPacket(endKeyBytes, endKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetPageDownKey = new DatagramPacket(pageDownKeyBytes, pageDownKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetNumLockKey = new DatagramPacket(numLockKeyBytes, numLockKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetHomeKey = new DatagramPacket(homeKeyBytes, homeKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetBacktickKey = new DatagramPacket(backtickKeyBytes, backtickKeyBytes.length, inetAddress, portUDP);
        DatagramPacket packetPageUpKey = new DatagramPacket(pageUpKeyBytes, pageUpKeyBytes.length, inetAddress, portUDP);

        DatagramPacket spaceKeyReleasedPacket = new DatagramPacket(spaceKey_released_bytes, spaceKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket enterKeyReleasedPacket = new DatagramPacket(enterKey_released_bytes, enterKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket tabKeyReleasedPacket = new DatagramPacket(tabKey_released_bytes, tabKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket shiftKeyReleasedPacket = new DatagramPacket(shiftKey_released_bytes, shiftKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket ctrlKeyReleasedPacket = new DatagramPacket(ctrlKey_released_bytes, ctrlKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket altKeyReleasedPacket = new DatagramPacket(altKey_released_bytes, altKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket escKeyReleasedPacket = new DatagramPacket(escKey_released_bytes, escKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket backspaceKeyReleasedPacket = new DatagramPacket(backspaceKey_released_bytes, backspaceKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket capsLockKeyReleasedPacket = new DatagramPacket(capsLockKey_released_bytes, capsLockKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f1KeyReleasedPacket = new DatagramPacket(f1Key_released_bytes, f1Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f2KeyReleasedPacket = new DatagramPacket(f2Key_released_bytes, f2Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f3KeyReleasedPacket = new DatagramPacket(f3Key_released_bytes, f3Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f4KeyReleasedPacket = new DatagramPacket(f4Key_released_bytes, f4Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f5KeyReleasedPacket = new DatagramPacket(f5Key_released_bytes, f5Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f6KeyReleasedPacket = new DatagramPacket(f6Key_released_bytes, f6Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f7KeyReleasedPacket = new DatagramPacket(f7Key_released_bytes, f7Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f8KeyReleasedPacket = new DatagramPacket(f8Key_released_bytes, f8Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f9KeyReleasedPacket = new DatagramPacket(f9Key_released_bytes, f9Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f10KeyReleasedPacket = new DatagramPacket(f10Key_released_bytes, f10Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f11KeyReleasedPacket = new DatagramPacket(f11Key_released_bytes, f11Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket f12KeyReleasedPacket = new DatagramPacket(f12Key_released_bytes, f12Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num0KeyReleasedPacket = new DatagramPacket(num0Key_released_bytes, num0Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num1KeyReleasedPacket = new DatagramPacket(num1Key_released_bytes, num1Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num2KeyReleasedPacket = new DatagramPacket(num2Key_released_bytes, num2Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num3KeyReleasedPacket = new DatagramPacket(num3Key_released_bytes, num3Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num4KeyReleasedPacket = new DatagramPacket(num4Key_released_bytes, num4Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num5KeyReleasedPacket = new DatagramPacket(num5Key_released_bytes, num5Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num6KeyReleasedPacket = new DatagramPacket(num6Key_released_bytes, num6Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num7KeyReleasedPacket = new DatagramPacket(num7Key_released_bytes, num7Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num8KeyReleasedPacket = new DatagramPacket(num8Key_released_bytes, num8Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket num9KeyReleasedPacket = new DatagramPacket(num9Key_released_bytes, num9Key_released_bytes.length, inetAddress, portUDP);
        DatagramPacket aKeyReleasedPacket = new DatagramPacket(aKey_released_bytes, aKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket bKeyReleasedPacket = new DatagramPacket(bKey_released_bytes, bKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket cKeyReleasedPacket = new DatagramPacket(cKey_released_bytes, cKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket dKeyReleasedPacket = new DatagramPacket(dKey_released_bytes, dKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket eKeyReleasedPacket = new DatagramPacket(eKey_released_bytes, eKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket fKeyReleasedPacket = new DatagramPacket(fKey_released_bytes, fKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket gKeyReleasedPacket = new DatagramPacket(gKey_released_bytes, gKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket hKeyReleasedPacket = new DatagramPacket(hKey_released_bytes, hKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket iKeyReleasedPacket = new DatagramPacket(iKey_released_bytes, iKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket jKeyReleasedPacket = new DatagramPacket(jKey_released_bytes, jKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket kKeyReleasedPacket = new DatagramPacket(kKey_released_bytes, kKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket lKeyReleasedPacket = new DatagramPacket(lKey_released_bytes, lKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket mKeyReleasedPacket = new DatagramPacket(mKey_released_bytes, mKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket nKeyReleasedPacket = new DatagramPacket(nKey_released_bytes, nKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket oKeyReleasedPacket = new DatagramPacket(oKey_released_bytes, oKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket pKeyReleasedPacket = new DatagramPacket(pKey_released_bytes, pKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket qKeyReleasedPacket = new DatagramPacket(qKey_released_bytes, qKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket rKeyReleasedPacket = new DatagramPacket(rKey_released_bytes, rKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket sKeyReleasedPacket = new DatagramPacket(sKey_released_bytes, sKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket tKeyReleasedPacket = new DatagramPacket(tKey_released_bytes, tKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket uKeyReleasedPacket = new DatagramPacket(uKey_released_bytes, uKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket vKeyReleasedPacket = new DatagramPacket(vKey_released_bytes, vKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket wKeyReleasedPacket = new DatagramPacket(wKey_released_bytes, wKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket xKeyReleasedPacket = new DatagramPacket(xKey_released_bytes, xKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket yKeyReleasedPacket = new DatagramPacket(yKey_released_bytes, yKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket zKeyReleasedPacket = new DatagramPacket(zKey_released_bytes, zKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket deleteKeyReleasedPacket = new DatagramPacket(deleteKey_released_bytes, deleteKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket equalKeyReleasedPacket = new DatagramPacket(equalKey_released_bytes, equalKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket minusKeyReleasedPacket = new DatagramPacket(minusKey_released_bytes, minusKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket endKeyReleasedPacket = new DatagramPacket(endKey_released_bytes, endKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket pageDownKeyReleasedPacket = new DatagramPacket(pageDownKey_released_bytes, pageDownKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket numLockKeyReleasedPacket = new DatagramPacket(numLockKey_released_bytes, numLockKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket homeKeyReleasedPacket = new DatagramPacket(homeKey_released_bytes, homeKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket backtickKeyReleasedPacket = new DatagramPacket(backtickKey_released_bytes, backtickKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket pageUpKeyReleasedPacket = new DatagramPacket(pageUpKey_released_bytes, pageUpKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket quoteKeyReleasedPacket = new DatagramPacket(quoteKey_released_bytes, quoteKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket semicolonKeyReleasedPacket = new DatagramPacket(semicolonKey_released_bytes, semicolonKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket periodKeyReleasedPacket = new DatagramPacket(periodKey_released_bytes, periodKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket commaKeyReleasedPacket = new DatagramPacket(commaKey_released_bytes, commaKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket arrowUpKeyReleasedPacket = new DatagramPacket(arrowUpKey_released_bytes, arrowUpKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket arrowDownKeyReleasedPacket = new DatagramPacket(arrowDownKey_released_bytes, arrowDownKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket closeBracketKeyReleasedPacket = new DatagramPacket(closeBracketKey_released_bytes, closeBracketKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket openBracketKeyReleasedPacket = new DatagramPacket(openBracketKey_released_bytes, openBracketKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket arrowLeftKeyReleasedPacket = new DatagramPacket(arrowLeftKey_released_bytes, arrowLeftKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket arrowRightKeyReleasedPacket = new DatagramPacket(arrowRightKey_released_bytes, arrowRightKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket backslashKeyReleasedPacket = new DatagramPacket(backslashKey_released_bytes, backslashKey_released_bytes.length, inetAddress, portUDP);
        DatagramPacket forwardSlashKeyReleasedPacket = new DatagramPacket(forwardSlashKey_released_bytes, forwardSlashKey_released_bytes.length, inetAddress, portUDP);



        byte[] quoteKeyBytes = quoteKey.getBytes();
        DatagramPacket packetQuoteKey = new DatagramPacket(quoteKeyBytes, quoteKeyBytes.length, inetAddress, portUDP);

        byte[] periodKeyBytes = periodKey.getBytes();
        DatagramPacket packetPeriodKey = new DatagramPacket(periodKeyBytes, periodKeyBytes.length, inetAddress, portUDP);

        byte[] commaKeyBytes = commaKey.getBytes();
        DatagramPacket packetCommaKey = new DatagramPacket(commaKeyBytes, commaKeyBytes.length, inetAddress, portUDP);

        byte[] arrowUpKeyBytes = arrowUpKey.getBytes();
        DatagramPacket packetArrowUpKey = new DatagramPacket(arrowUpKeyBytes, arrowUpKeyBytes.length, inetAddress, portUDP);

        byte[] arrowDownKeyBytes = arrowDownKey.getBytes();
        DatagramPacket packetArrowDownKey = new DatagramPacket(arrowDownKeyBytes, arrowDownKeyBytes.length, inetAddress, portUDP);

        byte[] arrowLeftKeyBytes = arrowLeftKey.getBytes();
        DatagramPacket packetArrowLeftKey = new DatagramPacket(arrowLeftKeyBytes, arrowLeftKeyBytes.length, inetAddress, portUDP);

        byte[] arrowRightKeyBytes = arrowRightKey.getBytes();
        DatagramPacket packetArrowRightKey = new DatagramPacket(arrowRightKeyBytes, arrowRightKeyBytes.length, inetAddress, portUDP);

        byte[] forwardSlashKeyBytes = forwardSlashKey.getBytes();
        DatagramPacket packetForwardSlashKey = new DatagramPacket(forwardSlashKeyBytes, forwardSlashKeyBytes.length, inetAddress, portUDP);

        byte[] backslashKeyBytes = backslashKey.getBytes();
        DatagramPacket packetBackslashKey = new DatagramPacket(backslashKeyBytes, backslashKeyBytes.length, inetAddress, portUDP);

        byte[] openBracketKeyBytes = openBracketKey.getBytes();
        DatagramPacket packetOpenBracketKey = new DatagramPacket(openBracketKeyBytes, openBracketKeyBytes.length, inetAddress, portUDP);

        byte[] closeBracketKeyBytes = closeBracketKey.getBytes();
        DatagramPacket packetCloseBracketKey = new DatagramPacket(closeBracketKeyBytes, closeBracketKeyBytes.length, inetAddress, portUDP);

        byte[] semicolonKeyBytes = semicolonKey.getBytes();
        DatagramPacket packetSemicolonKey = new DatagramPacket(semicolonKeyBytes, semicolonKeyBytes.length, inetAddress, portUDP);

        mouseWheelListener= new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                System.out.println("Mouse Wheel Moved " + notches + " notches.");
                if (notches == 1) {
                    try {
                        datagramSocket.send(packet_mouseScrollDown);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (notches == -1){
                    try {
                        datagramSocket.send(packet_mouseScrollUp);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };
        keyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key Pressed: " + e.getKeyChar());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SHIFT -> {
                        if (!shiftPress) {
                            try {
                                System.out.println("Shift Pressed");
                                datagramSocket.send(packet_shiftKey_pressed);
                                shiftPress = true;
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    case KeyEvent.VK_CONTROL -> {
                        if (!ctrlPress) {
                            try {
                                System.out.println("Control Pressed");
                                datagramSocket.send(packet_ctrlKey_pressed);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    case KeyEvent.VK_A -> {
                        try {
                            datagramSocket.send(packet_aKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_B -> {
                        try {
                            datagramSocket.send(packet_bKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_C -> {
                        try {
                            datagramSocket.send(packet_cKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_D -> {
                        try {
                            datagramSocket.send(packet_dKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_E -> {
                        try {
                            datagramSocket.send(packet_eKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F -> {
                        try {
                            datagramSocket.send(packet_fKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_G -> {
                        try {
                            datagramSocket.send(packet_gKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_H -> {
                        try {
                            datagramSocket.send(packet_hKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_I -> {
                        try {
                            datagramSocket.send(packet_iKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_J -> {
                        try {
                            datagramSocket.send(packet_jKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_K -> {
                        try {
                            datagramSocket.send(packet_kKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_L -> {
                        try {
                            datagramSocket.send(packet_lKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_M -> {
                        try {
                            datagramSocket.send(packet_mKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_N -> {
                        try {
                            datagramSocket.send(packet_nKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_O -> {
                        try {
                            datagramSocket.send(packet_oKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_P -> {
                        try {
                            datagramSocket.send(packet_pKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Q -> {
                        try {
                            datagramSocket.send(packet_qKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_R -> {
                        try {
                            datagramSocket.send(packet_rKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_S -> {
                        try {
                            datagramSocket.send(packet_sKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_T -> {
                        try {
                            datagramSocket.send(packet_tKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_U -> {
                        try {
                            datagramSocket.send(packet_uKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_V -> {
                        try {
                            datagramSocket.send(packet_vKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_W -> {
                        try {
                            datagramSocket.send(packet_wKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_X -> {
                        try {
                            datagramSocket.send(packet_xKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Y -> {
                        try {
                            datagramSocket.send(packet_yKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Z -> {
                        try {
                            datagramSocket.send(packet_zKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '0' -> {
                        try {
                            datagramSocket.send(packet_num0Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '1' -> {
                        try {
                            datagramSocket.send(packet_num1Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '2' -> {
                        try {
                            datagramSocket.send(packet_num2Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '3' -> {
                        try {
                            datagramSocket.send(packet_num3Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '4' -> {
                        try {
                            datagramSocket.send(packet_num4Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '5' -> {
                        try {
                            datagramSocket.send(packet_num5Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '6' -> {
                        try {
                            datagramSocket.send(packet_num6Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '7' -> {
                        try {
                            datagramSocket.send(packet_num7Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '8' -> {
                        try {
                            datagramSocket.send(packet_num8Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '9' -> {
                        try {
                            datagramSocket.send(packet_num9Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case ' ' -> {
                        try {
                            datagramSocket.send(packet_spaceKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\n' -> {
                        try {
                            datagramSocket.send(packet_enterKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\b' -> {
                        try {
                            datagramSocket.send(packet_backspaceKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\u001B' -> {
                        try {
                            datagramSocket.send(packet_escKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F1 -> {
                        System.out.println("F1 Key");
                        try {
                            datagramSocket.send(packet_f1Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F2 -> {
                        System.out.println("F2 Key");
                        try {
                            datagramSocket.send(packet_f2Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F3 -> {
                        System.out.println("F3 Key");
                        try {
                            datagramSocket.send(packet_f3Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }

                    }
                    case KeyEvent.VK_F4 -> {
                        System.out.println("F4 Key");
                        try {
                            datagramSocket.send(packet_f4Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F5 -> {
                        System.out.println("F5 Key");
                        try {
                            datagramSocket.send(packet_f5Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F6 -> {
                        System.out.println("F6 Key");
                        try {
                            datagramSocket.send(packet_f6Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F7 -> {
                        System.out.println("F7 Key");
                        try {
                            datagramSocket.send(packet_f7Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F8 -> {
                        System.out.println("F8 Key");
                        try {
                            datagramSocket.send(packet_f8Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F9 -> {
                        System.out.println("F9 Key");
                        try {
                            datagramSocket.send(packet_f9Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F10 -> {
                        System.out.println("F10 Key");
                        try {
                            datagramSocket.send(packet_f10Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F11 -> {
                        System.out.println("F11 Key");
                        try {
                            datagramSocket.send(packet_f11Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_F12 -> {
                        System.out.println("F12 Key");
                        try {
                            datagramSocket.send(packet_f12Key_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_DELETE -> {
                        System.out.println("Delete Key");
                        try {
                            datagramSocket.send(packet_deleteKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_OPEN_BRACKET -> {
                        System.out.println("Open Bracket Key");
                        try {
                            datagramSocket.send(packetOpenBracketKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_CLOSE_BRACKET -> {
                        System.out.println("Close Bracket Key");
                        try {
                            datagramSocket.send(packetCloseBracketKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_SEMICOLON -> {

                        System.out.println("Semicolon Key");
                        try {
                            datagramSocket.send(packetSemicolonKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_SLASH -> {
                        System.out.println("Slash Key");
                        try {
                            datagramSocket.send(packetForwardSlashKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_BACK_SLASH -> {
                        System.out.println("Backslash Key");
                        try {
                            datagramSocket.send(packetBackslashKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_QUOTE -> {
                        System.out.println("Quote Key");
                        try {
                            datagramSocket.send(packetQuoteKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_UP -> {

                        System.out.println("Up Arrow Key");
                        try {
                            datagramSocket.send(packetArrowUpKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN -> {

                        System.out.println("Down Arrow Key");
                        try {
                            datagramSocket.send(packetArrowDownKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_LEFT -> {
                        System.out.println("Left Arrow Key");
                        try {
                            datagramSocket.send(packetArrowLeftKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT -> {

                        System.out.println("Right Arrow Key");
                        try {
                            datagramSocket.send(packetArrowRightKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    // Comma Key
                    case KeyEvent.VK_COMMA -> {
                        System.out.println("Comma Key");
                        try {
                            datagramSocket.send(packetCommaKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_PERIOD -> {
                        System.out.println("Period Key");
                        try {
                            datagramSocket.send(packetPeriodKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_EQUALS -> { // Equals (=) key
                        System.out.println("Equals (=) Key");
                        try {
                            datagramSocket.send(packetEqualKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_MINUS -> {
                        // Minus (-) key
                        System.out.println("Minus (-) Key");
                        try {
                            datagramSocket.send(packetMinusKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_END -> { // End key
                        System.out.println("End Key");
                        try {
                            datagramSocket.send(packetEndKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_PAGE_UP-> {
                        System.out.println("Page Up Key");
                        try {
                            datagramSocket.send(packetPageUpKey);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_PAGE_DOWN-> { // Page Down key
                        System.out.println("Page Down Key");
                        try {
                            datagramSocket.send(packetPageDownKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_NUM_LOCK-> { // Num Lock key
                        System.out.println("Num Lock Key");
                        try {
                            datagramSocket.send(packetNumLockKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case KeyEvent.VK_HOME-> { // Home key
                        System.out.println("Home Key");
                        try {
                            datagramSocket.send(packetHomeKey);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case 192 -> {
                        // Backtick/Grave Accent key
                        System.out.println("Backtick/Grave Accent Key");
                        try {
                            datagramSocket.send(packetBacktickKey);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_CAPS_LOCK -> {
                        System.out.println("Caps Lock Key");
                        try {
                            datagramSocket.send(packet_capsLockKey_pressed);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SHIFT -> {
                        try {
                            System.out.println("Shift Released");
                            datagramSocket.send(shiftKeyReleasedPacket);
                            shiftPress = false;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);

                        }
                    }
                    case KeyEvent.VK_ALT -> {
                        try {
                            System.out.println("Alt Released");
                            datagramSocket.send(altKeyReleasedPacket);
                            altPress = false;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);

                        }
                    }
                    case KeyEvent.VK_CONTROL -> {
                        try {
                            System.out.println("Control Released");
                            datagramSocket.send(ctrlKeyReleasedPacket);
                            ctrlPress = false;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_A -> {
                        try {
                            datagramSocket.send(aKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_B -> {
                        try {
                            datagramSocket.send(bKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_C -> {
                        try {
                            datagramSocket.send(cKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_D -> {
                        try {
                            datagramSocket.send(dKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_E -> {
                        try {
                            datagramSocket.send(eKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F -> {
                        try {
                            datagramSocket.send(fKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_G -> {
                        try {
                            datagramSocket.send(gKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_H -> {
                        try {
                            datagramSocket.send(hKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_I -> {
                        try {
                            datagramSocket.send(iKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_J -> {
                        try {
                            datagramSocket.send(jKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_K -> {
                        try {
                            datagramSocket.send(kKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_L -> {
                        try {
                            datagramSocket.send(lKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_M -> {
                        try {
                            datagramSocket.send(mKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_N -> {
                        try {
                            datagramSocket.send(nKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_O -> {
                        try {
                            datagramSocket.send(oKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_P -> {
                        try {
                            datagramSocket.send(pKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Q -> {
                        try {
                            datagramSocket.send(qKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_R -> {
                        try {
                            datagramSocket.send(rKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_S -> {
                        try {
                            datagramSocket.send(sKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_T -> {
                        try {
                            datagramSocket.send(tKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_U -> {
                        try {
                            datagramSocket.send(uKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_V -> {
                        try {
                            datagramSocket.send(vKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_W -> {
                        try {
                            datagramSocket.send(wKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_X -> {
                        try {
                            datagramSocket.send(xKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Y -> {
                        try {
                            datagramSocket.send(yKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_Z -> {
                        try {
                            datagramSocket.send(zKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '0' -> {
                        try {
                            datagramSocket.send(num0KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '1' -> {
                        try {
                            datagramSocket.send(num1KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '2' -> {
                        try {
                            datagramSocket.send(num2KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '3' -> {
                        try {
                            datagramSocket.send(num3KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '4' -> {
                        try {
                            datagramSocket.send(num4KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '5' -> {
                        try {
                            datagramSocket.send(num5KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '6' -> {
                        try {
                            datagramSocket.send(num6KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '7' -> {
                        try {
                            datagramSocket.send(num7KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '8' -> {
                        try {
                            datagramSocket.send(num8KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '9' -> {
                        try {
                            datagramSocket.send(num9KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case ' ' -> {
                        try {
                            datagramSocket.send(spaceKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\n' -> {
                        try {
                            datagramSocket.send(enterKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\t' -> {
                        try {
                            datagramSocket.send(tabKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\b' -> {
                        try {
                            datagramSocket.send(backspaceKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case '\u001B' -> {
                        try {
                            datagramSocket.send(escKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F1 -> {
                        System.out.println("F1 Key");
                        try {
                            datagramSocket.send(f1KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F2 -> {
                        System.out.println("F2 Key");
                        try {
                            datagramSocket.send(f2KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F3 -> {
                        System.out.println("F3 Key");
                        try {
                            datagramSocket.send(f3KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F4 -> {
                        System.out.println("F4 Key");
                        try {
                            datagramSocket.send(f4KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F5 -> {
                        System.out.println("F5 Key");
                        try {
                            datagramSocket.send(f5KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F6 -> {
                        System.out.println("F6 Key");
                        try {
                            datagramSocket.send(f6KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F7 -> {
                        System.out.println("F7 Key");
                        try {
                            datagramSocket.send(f7KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F8 -> {
                        System.out.println("F8 Key");
                        try {
                            datagramSocket.send(f8KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F9 -> {
                        System.out.println("F9 Key");
                        try {
                            datagramSocket.send(f9KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F10 -> {
                        System.out.println("F10 Key");
                        try {
                            datagramSocket.send(f10KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F11 -> {
                        System.out.println("F11 Key");
                        try {
                            datagramSocket.send(f11KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_F12 -> {
                        System.out.println("F12 Key");
                        try {
                            datagramSocket.send(f12KeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_DELETE -> {
                        System.out.println("Delete Key");
                        try {
                            datagramSocket.send(deleteKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_OPEN_BRACKET -> {
                        System.out.println("Open Bracket Key");
                        try {
                            datagramSocket.send(openBracketKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_CLOSE_BRACKET -> {
                        System.out.println("Close Bracket Key");
                        try {
                            datagramSocket.send(closeBracketKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_SEMICOLON -> {
                        System.out.println("Semicolon Key");
                        try {
                            datagramSocket.send(semicolonKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_SLASH -> {
                        System.out.println("Slash Key");
                        try {
                            datagramSocket.send(forwardSlashKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_BACK_SLASH -> {
                        System.out.println("Backslash Key");
                        try {
                            datagramSocket.send(backslashKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_QUOTE -> {
                        System.out.println("Quote Key");
                        try {
                            datagramSocket.send(quoteKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_UP -> {
                        System.out.println("Up Arrow Key");
                        try {
                            datagramSocket.send(arrowUpKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_DOWN -> {
                        System.out.println("Down Arrow Key");
                        try {
                            datagramSocket.send(arrowDownKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_LEFT -> {
                        System.out.println("Left Arrow Key");
                        try {
                            datagramSocket.send(arrowLeftKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_RIGHT -> {
                        System.out.println("Right Arrow Key");
                        try {
                            datagramSocket.send(arrowRightKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    // Comma Key
                    case KeyEvent.VK_COMMA -> {
                        System.out.println("Comma Key");
                        try {
                            datagramSocket.send(commaKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_PERIOD -> {
                        System.out.println("Period Key");
                        try {
                            datagramSocket.send(periodKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_EQUALS -> { // Equals (=) key
                        System.out.println("Equals (=) Key");
                        try {
                            datagramSocket.send(equalKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_MINUS -> { // Minus (-) key
                        System.out.println("Minus (-) Key");
                        try {
                            datagramSocket.send(minusKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_END -> { // End key
                        System.out.println("End Key");
                        try {
                            datagramSocket.send(endKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_PAGE_UP -> {
                        System.out.println("Page Up Key");
                        try {
                            datagramSocket.send(pageUpKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_PAGE_DOWN -> { // Page Down key
                        System.out.println("Page Down Key");
                        try {
                            datagramSocket.send(pageDownKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_NUM_LOCK -> { // Num Lock key
                        System.out.println("Num Lock Key");
                        try {
                            datagramSocket.send(numLockKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case KeyEvent.VK_HOME -> { // Home key
                        System.out.println("Home Key");
                        try {
                            datagramSocket.send(homeKeyReleasedPacket);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case 192 -> { // Backtick/Grave Accent key
                        System.out.println("Backtick/Grave Accent Key");
                        try {
                            datagramSocket.send(backtickKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                    case KeyEvent.VK_CAPS_LOCK -> {
                        System.out.println("Caps Lock Key");
                        try {
                            datagramSocket.send(capsLockKeyReleasedPacket);
                        } catch (IOException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        };

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Left Button Pressed");

                    try {
                        datagramSocket.send(packetLeftPressed);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    System.out.println("Middle Button Pressed");

                    try {
                        datagramSocket.send(packetMiddlePressed);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Right Button Pressed");

                    try {
                        datagramSocket.send(packetRightPressed);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Left Button Released");

                    try {
                        datagramSocket.send(packetLeftReleased);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    System.out.println("Middle Button Released");

                    try {
                        datagramSocket.send(packetMiddleReleased);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Right Button Released");

                    try {
                        datagramSocket.send(packetRightReleased);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };

        System.out.println("Adding keyListener and MouseListener");
        jFrame.addMouseListener(mouseAdapter);
        jFrame.addKeyListener(keyListener);
        jFrame.addMouseWheelListener(mouseWheelListener);

    }
    public void removeEventListeners() {
        jFrame.removeKeyListener(keyListener);
        jFrame.removeMouseListener(mouseAdapter);
        jFrame.removeMouseWheelListener(mouseWheelListener);
        System.out.println("KeyListener removed");
    }
}