package HF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Arrays;

public class PongPanel extends JPanel {
     private Player player;
     private Bot bot;
     private Ball ball;
     private JLabel label;
     private boolean firstpush = false;
     private volatile boolean ispause = false;
     private int highscores[]=new int[5];

    public PongPanel(JPanel cards) {
        CardLayout c = (CardLayout)(cards.getLayout());
        for (int i=0; i<5; i++)
            highscores[i]=0;
         addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            public void keyReleased(KeyEvent e) {
               player.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P){
                    c.next(cards);
                    firstpush = true;
                    ispause = true;
                    try {
                        output(highscores);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else
                try {
                    player.keyPressed(e);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
         });
        setFocusable(true);
        label = new JLabel();
        this.add(label);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //csak hogy szebb legyen az összes tárgy
        player.paint(g2d);
        ball.paint(g2d);
        bot.paint(g2d);
    }

    public void setPlayer(Player p){
        player = p;
    }

    public void setBot(Bot b){
        bot=b;
    }

    public void setBall(Ball b){
        ball = b;
    }

    public  void setpoints(int player, int bot){
        label.setText(player + " : " + bot);
    }

    public void setSavePoints(int p, int b)
    {
        player.setpoints(p);
        bot.setpoints(b);
    }

    public int getPlayerPoint(){return player.getpoints();}

    public int getBotPoint(){ return bot.getpoints(); }

    public boolean getfirstpush(){ return firstpush; }

    public boolean getispause() { return ispause; }

    public void changeispuse(){ ispause = !ispause; }

    public int[] geths() { return highscores; }

    public void setgivenhs(int where, int with)
    {
        highscores[where] = with;
    }

    public void sorths() { Arrays.sort(highscores); }

    public void output(int[] points) throws IOException {
        FileWriter fw = new FileWriter("savehighscore.txt");
        PrintWriter pw = new PrintWriter(fw);
        for(int i =0; i<5; i++){
            pw.println(points[i]);
        }
        pw.close();
    }

    public void resetballlfrompongpanel(){
        ball.resetballpos();
    }
}
