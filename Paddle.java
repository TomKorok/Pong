package HF;

import javax.swing.*;

public class Paddle {
    protected int paddleheight=60;
    protected int paddlewidth=10;
    protected int x=30;
    protected int y = 0;
    protected int ydir=0;
    protected JPanel panel;
    protected int points=0;

    public void setpoints(int mire){ points=mire; }

    public int getpoints(){ return points; }

    public void increasepoints(){ points++; }

    public void move() {
        if (y + ydir > 0 && y + ydir < panel.getHeight()-60)
            y += ydir;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }
}
