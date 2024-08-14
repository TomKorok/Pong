package HF;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball {
     private JPanel panel;
     private int ballx = 320;
     private int bally = 180;
     private int xdir;
     private int ydir;
     private int bh=20;
     private int bw=20;
     private Player player;
     private Bot bot;

    public Ball(PongPanel p, Player pr, Bot b) {
        this.panel = p;
        player = pr;
        bot = b;
        Random rand = new Random();
        int rngballdir = rand.nextInt(4);
        switch (rngballdir) {
            case 0:
                xdir = -1;
                ydir = -1;
                break;
            case 1:
                xdir = -1;
                ydir = 1;
                break;
            case 2:
                xdir = 1;
                ydir = -1;
                break;
            default:
                xdir = 1;
                ydir = 1;
                break;
        }
    }

    public int getydir(){ return ydir; }

    public int gety(){ return bally; }

    public int getx() { return ballx; }

    public int getxdir(){ return xdir; }

    public void moveBall(int px, int botx, int py, int boty) {
        detectPlayerCollision(px, py);
        detectBotCollision(botx, boty);
        detectWallCollision(panel.getHeight(),panel.getWidth());
        ballx += xdir;
        bally += ydir;
    }

    public void detectPlayerCollision(int px, int py){
        if (ballx < px+10 && bally+20 > py && bally <py+60  ){
            xdir = 1;
        }
    }

    public void detectBotCollision(int botx, int boty){
        int collisionThreshhold= 5;
        if(botx- ballx -bw<collisionThreshhold && botx-ballx-bw>-collisionThreshhold){
            if(bally+20 >=boty-collisionThreshhold&& bally <boty+60+collisionThreshhold){
                xdir=-1;
            }

        }
    }
    public void detectWallCollision(int height, int width){
        if (bally < 0){
            ydir = 1;
        }
        if (bally > height - 20){
            ydir = -1;
        }
        if(ballx <0){
            bot.increasepoints();
            resetballpos();
        }
        if(ballx >width){
            player.increasepoints();
            resetballpos();
        }
    }

    public void resetballpos() {
        ballx = 320;
        bally = 180;
        Random rand = new Random();
        int rngballdir = rand.nextInt(4);
        switch (rngballdir) {
            case 0:
                xdir = -1;
                ydir = -1;
                break;
            case 1:
                xdir = -1;
                ydir = 1;
                break;
            case 2:
                xdir = 1;
                ydir = -1;
                break;
            default:
                xdir = 1;
                ydir = 1;
                break;
        }
    }

    public void paint(Graphics2D g){ g.fillOval(ballx, bally, bw, bh); }

    public void setBallx(int x) {
        ballx = x;
    }

    public void setBally(int y) {
        bally = y;
    }

    public void setXdir(int x){
        xdir=x;
    }

    public void setYdir(int y){
        ydir=y;
    }
}
