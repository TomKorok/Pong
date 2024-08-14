package HF;

import java.awt.*;
import java.util.Random;

public class Bot extends Paddle{

    public Bot(PongPanel p, int xpos) {
        panel= p;
        x=xpos;
    }

    public void move(int bally, int ballx, int ballxdir) {
        Random rand = new Random();
        int rng = rand.nextInt(3) * 30;
        if (ballxdir == 1 && ballx > 500 + rng) {
            if (bally > y && y < panel.getHeight() - 60) {
                ydir = 1;
            } else if (bally < y && y > 0) {
                ydir = -1;
            } else if (y > panel.getHeight() - 60 || y < 0) {
                ydir = 0;
            }
        } else {
            if (y < 0) {
                ydir = 1;
            }
            if (y > panel.getHeight() - 60) {
                ydir = -1;
            }
        }
        y += ydir;
    }

    public void paint(Graphics2D g){
        g.fillRect(x, y, paddlewidth, paddleheight);
    }
}