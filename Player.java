package HF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Paddle {
    public Player(PongPanel p) { panel = p; }

    public void keyReleased(KeyEvent e) {
        ydir = 0;
    }

    public void keyPressed(KeyEvent e) throws InterruptedException {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ydir = 1;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ydir = -1;
    }

    public void paint(Graphics2D g){
        g.fillRect(x, y, paddlewidth, paddleheight);
    }
}
