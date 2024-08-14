package HF;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class PongPanelTest {
    PongPanel pongpanel;
    MenuPanel menupanel;
    JPanel panel;
    @Before
    public void setUp() throws Exception {
        panel = new JPanel(new CardLayout());
        pongpanel = new PongPanel(panel);
        menupanel = new MenuPanel(pongpanel, panel);
        int[] outputarray=new int[5];
        for(int i=0; i<5; i++)
        {
            pongpanel.setgivenhs(i, 0);
            outputarray[i]=i+1;
        }
        pongpanel.output(outputarray);
    }

    @Test
    public void highscore_save_load() throws IOException {
        menupanel.inpuths();
        Assert.assertEquals(1, pongpanel.geths()[0]);
        Assert.assertEquals(2, pongpanel.geths()[1]);
        Assert.assertEquals(3, pongpanel.geths()[2]);
        Assert.assertEquals(4, pongpanel.geths()[3]);
        Assert.assertEquals(5, pongpanel.geths()[4]);
    }
}