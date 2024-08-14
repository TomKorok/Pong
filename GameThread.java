package HF;

import javax.swing.*;
import java.awt.*;

public class GameThread extends Thread{
     private PongPanel pp;
     private Ball ball;
     private Player player;
     private Bot bot;

     public GameThread(PongPanel pongpanel){
        pp=pongpanel;
        bot = new Bot(pp, 580);
        player = new Player(pp);
        ball = new Ball(pp,player,bot);
        pp.setPlayer(player);
        pp.setBot(bot);
        pp.setBall(ball);
     }
    @Override
    public void run(){ gameLoop(); }

    public void gameLoop() {
        while (true) {
            if (!pp.getispause()) {
                pp.grabFocus();
                moveComponents();
                pp.setpoints(player.getpoints(), bot.getpoints());
                pp.repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (bot.getpoints() == 0) {
                    if (player.getpoints() > pp.geths()[0]) {
                        pp.setgivenhs(0, player.getpoints());
                    }
                }
            }
        }
    }
    public void moveComponents() {
        ball.moveBall(player.getx(), bot.getx(), player.gety(), bot.gety());
        player.move();
        bot.move(ball.gety(), ball.getx(), ball.getxdir());
    }
}
