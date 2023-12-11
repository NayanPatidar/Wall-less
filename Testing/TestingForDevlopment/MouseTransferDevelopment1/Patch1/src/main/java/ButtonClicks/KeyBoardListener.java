package ButtonClicks;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {

    public static void main(String[] args) {
        KeyBoardListener example = new KeyBoardListener();
        example.createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Keyboard Listener Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this); // Register the listener with the JFrame
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
    }
}
