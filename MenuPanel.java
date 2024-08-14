package HF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MenuPanel extends JPanel{
     private PongPanel pongpanel;
     private int savepoints[] = new int[2];
     private JButton newgame;
     private JButton loadgame;
     private JButton continue1;
     private JButton savegame;

    public MenuPanel(PongPanel pp, JPanel cards){
        pongpanel = pp;
        CardLayout c = (CardLayout)(cards.getLayout());
        newgame = new JButton("New Game");
        loadgame = new JButton("Load Game");
        continue1 = new JButton("Continue");
        savegame = new JButton("Save Game");
        GameThread gt = new GameThread(pongpanel);
        savepoints[0]=0;
        savepoints[1]=0;

        newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pongpanel.resetballlfrompongpanel();
                c.next(cards);
                pongpanel.setSavePoints(0,0);
                if(!pongpanel.getispause()){
                    gt.start();
                }
                else
                    pongpanel.changeispuse();
            }
        });
        loadgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pongpanel.resetballlfrompongpanel();
                c.next(cards);
                try {
                    inputsavepoints();
                } catch (IOException e) {

                }
                pongpanel.setSavePoints(savepoints[0], savepoints[1]);
                if(!pongpanel.getispause()){
                    gt.start();
                }
                else
                    pongpanel.changeispuse();
            }
        });
        continue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(pongpanel.getfirstpush()){
                    c.next(cards);
                    pongpanel.changeispuse();
                }
            }
        });
        savegame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    output();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.add(newgame);
        this.add(loadgame);
        this.add(continue1);
        this.add(savegame);
    }

    public void inputsavepoints() throws IOException {
        FileReader fr =new FileReader("savegame.txt");
        BufferedReader br = new BufferedReader(fr);
        savepoints[0] = Integer.parseInt(br.readLine());
        savepoints[1] = Integer.parseInt(br.readLine());
        br.close();
    }

    public void inpuths() throws IOException {
        FileReader fr =new FileReader("savehighscore.txt");
        BufferedReader br = new BufferedReader(fr);
        for(int i=0; i<5; i++){
            pongpanel.setgivenhs(i, Integer.parseInt(br.readLine()));
        }
        br.close();
    }

    public void output() throws IOException {
        FileWriter fw = new FileWriter("savegame.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(pongpanel.getPlayerPoint());
        pw.println(pongpanel.getBotPoint());
        pw.close();
    }
}
