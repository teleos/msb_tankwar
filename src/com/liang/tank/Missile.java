package com.liang.tank;

import java.awt.*;
import java.util.List;

/**
 * 子彈類
 */
public class Missile {

    public static final int M_WIDTH = 5,M_HEIGHT = 5;

    //子彈速度
    public static final int X_SPEED = 10,Y_SPEED = 10;
    private int x ,y;  // 子彈坐標
    private Tank.Direction dir; //子彈方向
    private boolean aLive ; // 子彈存活
    private Tank tank;//子弹所处的坦克
    private Boolean good = true;
    private TankClient client;
    public Missile(int x , int y, Tank.Direction dir,Tank tank,TankClient c){
        this.dir = dir;
        this.tank = tank;
        client = c;
        aLive = true;

        good = tank.getGood();
        this.x = x;
        this.y = y;
        new Thread( ()->{
            while (aLive){
                try {
                    moving();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            List<Missile> missileList = tank.getMissileList();
            missileList.remove(this);

        } ).start();
    }


    /**
     * 子彈繪畫
     */
    public void draw(Graphics g){
        //獲取背景色
        Color c  = g.getColor();
        //設置子彈顔色
        g.setColor(Color.BLUE);
        //畫子彈
        g.fillOval(x,y,M_WIDTH,M_HEIGHT);
        //設置背景色
        g.setColor(c);
    }



    /**
     * 子彈移動
     */
    public void moving(){
            switch (dir){
                case L: x -= X_SPEED;break;
                case R: x += X_SPEED;break;
                case U: y -= Y_SPEED;break;
                case D: y += Y_SPEED;break;
                case LD: x-=X_SPEED;y+=Y_SPEED;break;
                case RD: x+=X_SPEED;y+=Y_SPEED;break;
                case RU: x+=X_SPEED;y-=Y_SPEED;break;
                case LU: x-=X_SPEED;y-=Y_SPEED;break;
            }

            if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT)
                aLive = false;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,M_WIDTH,M_HEIGHT);
    }

    /**
     * 是否集中坦克
     * @return true 為擊中 ，false為未擊中
     */
    public boolean hitTank(Tank tank){
        boolean intersects = tank.getRectangle().intersects(getRectangle());
        if (tank.isAlive() && intersects) {//若擊中， 坦克的狀態為死亡，子彈的狀態也為死亡
            tank.setAlive(false);
            this.aLive = false;

            Explode explode = new Explode(x, y);
            client.getExplodes().add(explode);
        }
        return  intersects;
    }


    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
}
