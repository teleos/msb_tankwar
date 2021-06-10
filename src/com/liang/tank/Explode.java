package com.liang.tank;

import java.awt.*;

public class Explode {
    private int x , y ;
    private int[] r = {4,7,12,18,26,32,49,30,14,6};
    private boolean alive = true;
    int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void draw(Graphics g) {

        Color color = g.getColor();

        g.setColor(Color.ORANGE);
        g.fillOval(x,y,r[step],r[step++]);

        if (step  == r.length)
            setAlive(false);

        g.setColor(color);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
