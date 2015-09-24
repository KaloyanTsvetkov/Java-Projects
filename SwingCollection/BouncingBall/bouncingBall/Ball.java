package bouncingBall;

import java.awt.Color;
import java.awt.Graphics;

import bouncingBall.BouncingBalls.Container;

public class Ball {

    private int x = random(480);
    private int y = random(480);
    private int speedX = random(30);
    private int speedY = random(30);
    private int radius = random(20);
    private int red = random(255);
    private int green = random(255);
    private int blue = random(255);
    private int i = 0;

    public int random(int maxRange) {
        return (int) Math.round(Math.random() * maxRange);
    }
    
    public void draw(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillOval((int) (x - radius), (int) (y - radius),
                (int) (2 * radius), (int) (2 * radius));

    }

    public void move(Container container) {
        x += speedX;
        y += speedY;

        if (x - radius < 0) {
            speedX = -speedX;
            x = radius;
        } else if (x + radius > 500) {
            speedX = -speedX;
            x = 500 - radius;
        }

        if (y - radius < 0) {
            speedY = -speedY;
            y = radius;
        } else if (y + radius > 500) {
            speedY = -speedY;
            y = 500 - radius;
        }
    }
}