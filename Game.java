package HF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pong");
        frame.setSize(640, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel cards = new JPanel(new CardLayout());

        PongPanel pongpanel = new PongPanel(cards);
        pongpanel.setBackground(Color.BLACK);

        MenuPanel menupanel = new MenuPanel(pongpanel, cards);
        menupanel.setBackground(Color.BLACK);

        try {
            menupanel.inpuths();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pongpanel.sorths();

        JLabel highscore = new JLabel("High Scores: "+ "1. : "+pongpanel.geths()[4]+" | 2. : "+pongpanel.geths()[3]+" | 3. : "+pongpanel.geths()[2]+" | 4. : "+pongpanel.geths()[1]+" | 5. : "+pongpanel.geths()[1]);
        JPanel forjlabel = new JPanel();
        forjlabel.setBackground(Color.BLACK);
        forjlabel.add(highscore);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(menupanel, BorderLayout.CENTER);
        container.add(forjlabel, BorderLayout.SOUTH);

        cards.add(container);
        cards.add(pongpanel);

        frame.add(cards);
        frame.setVisible(true);
    }

}
