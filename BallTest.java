package HF;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class BallTest {
    Ball ball;
    PongPanel panel;
    Player player;
    Bot bot;
    @Before
    public void setUp() throws Exception {
        ball=new Ball(panel, player, bot);
        bot = new Bot(panel, 600);
        ball.setXdir(-1);
        ball.setBallx(35);
        ball.setBally(80);
    }

    @Test
    public void test_player_collision(){
        final int expected = 1;
        ball.detectPlayerCollision(30, 40);
        Assert.assertEquals(expected, ball.getxdir());
    }

    @Test
    public void test_not_collision_to_player(){
        final int expected = -1;
        ball.detectPlayerCollision(30, 10);
        Assert.assertEquals(expected, ball.getxdir());
    }

    @Test
    public void test_bot_collision(){
        ball.setXdir(1);
        ball.setBallx(601);
        ball.setBally(80);
        final int expected  = -1;
        ball.detectBotCollision(600, 40);
        Assert.assertEquals(expected, ball.getxdir());
    }

    @Test
    public void test_not_collision_to_bot(){
        ball.setXdir(1);
        ball.setBallx(601);
        ball.setBally(80);
        final int expected  = 1;
        ball.detectBotCollision(600, 10);
        Assert.assertEquals(expected, ball.getxdir());
    }

    @Test
    public void test_wall_collision_upwall() {
        ball.setXdir(-1);
        ball.setYdir(-1);
        ball.setBallx(180);
        ball.setBally(-1);
        final int expectedXdir = -1;
        final int expectedYdir = 1;
        ball.detectWallCollision(640, 360);
        Assert.assertEquals(expectedXdir, ball.getxdir());
        Assert.assertEquals(expectedYdir, ball.getydir());
    }
    @Test
    public void test_wall_collision_downwall() {
        ball.setXdir(1);
        ball.setYdir(1);
        ball.setBallx(180);
        ball.setBally(641);
        final int expectedXdir = 1;
        final int expectedYdir = -1;
        ball.detectWallCollision(640, 320);
        Assert.assertEquals(expectedXdir, ball.getxdir());
        Assert.assertEquals(expectedYdir, ball.getydir());
    }
}