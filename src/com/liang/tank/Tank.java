package com.liang.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 坦克類
 */
public class Tank {

    private Random random = new Random();
    public static final int XSPEED = 5;
    public static final int YSPEED = 5;
    private  int WIDTH = 20;
    private  int HEIGHT = 20;
    private int x,y;
    private final List<Missile> missileList  = new CopyOnWriteArrayList<>();
    //是否按下四個方向鍵
    private boolean bL = false,bU = false,bR = false,bD = false;
    //炮筒方向
    private Direction missileDir = Direction.D;

    //成員變量：方向
    enum Direction{L,LU,U,RU,R,RD,D,LD}
    //坦克方向
    private Direction dir = Direction.U;
    //區分敵我
    private Boolean good;
    //是否存活
    private  boolean alive = true;
    //是否是移动状态
    private boolean isMoving = false;

    private TankClient client ;

    public Tank(int x,int y,boolean good ,TankClient c){
        this.x = x;
        this.y = y;
        this.good = good;
        client = c;

    }

    public void draw( Graphics g){
        Color c = g.getColor();
        if (good)
            g.setColor(Color.RED);
        else
            g.setColor(Color.MAGENTA);

        if (alive) {

            switch (dir){
                case D:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankD, x, y, null);
                        WIDTH = ResourceMgr.tankD.getWidth();
                        HEIGHT = ResourceMgr.tankD.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankD, x, y, null);
                        WIDTH = ResourceMgr.MainTankD.getWidth();
                        HEIGHT = ResourceMgr.MainTankD.getHeight();
                    }
                    break;
                case U:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankU, x, y, null);
                        WIDTH = ResourceMgr.tankU.getWidth();
                        HEIGHT = ResourceMgr.tankU.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankU, x, y, null);
                        WIDTH = ResourceMgr.MainTankU.getWidth();
                        HEIGHT = ResourceMgr.MainTankU.getHeight();
                    }
                    break;
                case L:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankL, x, y, null);
                        WIDTH = ResourceMgr.tankL.getWidth();
                        HEIGHT = ResourceMgr.tankL.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankL, x, y, null);
                        WIDTH = ResourceMgr.MainTankL.getWidth();
                        HEIGHT = ResourceMgr.MainTankL.getHeight();
                    }
                    break;
                case R:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankR, x, y, null);
                        WIDTH = ResourceMgr.tankR.getWidth();
                        HEIGHT = ResourceMgr.tankR.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankR, x, y, null);
                        WIDTH = ResourceMgr.MainTankR.getWidth();
                        HEIGHT = ResourceMgr.MainTankR.getHeight();
                    }
                    break;
                case RD:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankRD, x, y, null);
                        WIDTH = ResourceMgr.tankRD.getWidth();
                        HEIGHT = ResourceMgr.tankRD.getHeight();
                    }
                    else{
                        g.drawImage(ResourceMgr.MainTankRD,x,y,null);
                        WIDTH = ResourceMgr.MainTankRD.getWidth();
                        HEIGHT = ResourceMgr.MainTankRD.getHeight();
                    }
                    break;
                case LD:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankLD, x, y, null);
                        WIDTH = ResourceMgr.tankLD.getWidth();
                        HEIGHT = ResourceMgr.tankLD.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankLD, x, y, null);
                        WIDTH = ResourceMgr.MainTankLD.getWidth();
                        HEIGHT = ResourceMgr.MainTankLD.getHeight();
                    }
                    break;
                case LU:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankLU, x, y, null);
                        WIDTH = ResourceMgr.tankU.getWidth();
                        HEIGHT = ResourceMgr.tankLU.getHeight();
                    }
                    else {
                        g.drawImage(ResourceMgr.MainTankLU, x, y, null);
                        WIDTH = ResourceMgr.MainTankLU.getWidth();
                        HEIGHT = ResourceMgr.MainTankLU.getHeight();
                    }
                    break;
                case RU:
                    if (!good) {
                        g.drawImage(ResourceMgr.tankRU, x, y, null);
                        WIDTH = ResourceMgr.tankRU.getWidth();
                        HEIGHT = ResourceMgr.tankRU.getHeight();
                    }
                    else{
                        g.drawImage(ResourceMgr.MainTankRU,x,y,null);
                        WIDTH = ResourceMgr.MainTankRU.getWidth();
                        HEIGHT = ResourceMgr.MainTankRU.getHeight();
                    }
                    break;
            }

            moving();
        }
        for (Missile m: missileList) {
            m.draw(g);
        }
    }


    /**
     * 鼠標按下對坦克方向進行設置
     * @param e
     */
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                bL = true ;break;
            case KeyEvent.VK_UP:
                bU = true ;break;
            case KeyEvent.VK_RIGHT:
                bR = true ;break;
            case KeyEvent.VK_DOWN:
                bD = true ;break;
        }
        positioning();
    }

    /**
     * 鼠標鬆開 ，對坦克的方向進行設置
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                bL = false ;break;
            case KeyEvent.VK_UP:
                bU = false ;break;
            case KeyEvent.VK_RIGHT:
                bR = false ;break;
            case KeyEvent.VK_DOWN:
                bD = false ;break;
        }
        positioning();
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a')
            fire();

    }

    /**
     * 发射子弹
     */
    private void fire(){

        int missileX  = 0;
        int missileY = 0;

        switch (missileDir){
            case R:
                missileX = x + WIDTH;
                missileY = y + HEIGHT/2 ;
                break;
            case RU:
                missileX = x + WIDTH;
                missileY = y;
                break;
            case RD:
                missileX = x + WIDTH;
                missileY = y + HEIGHT;
                break;

            case U:
                missileX = x + WIDTH/2;
                missileY = y;
                break;
            case L:
                missileX = x ;
                missileY = y + HEIGHT/2;
                break;
            case D:
                missileX =  x + WIDTH/2;
                missileY= y + HEIGHT ;
                break;
            case LD:
                missileX = x ;
                missileY = y + HEIGHT;
                break;
            case LU:
                missileX = x;
                missileY = y;
                break;
        }


        missileList.add(new Missile(missileX,missileY,missileDir,this,    client));
    }

    /**
     * 坦克移動
     */
    private void moving() {
        if (!isMoving)
            return;
        switch (dir){
            case L  : x-=XSPEED;break;
            case D  : y+=YSPEED;break;
            case R  : x+=XSPEED;break;
            case U  : y-=YSPEED;break;
            case LD : x-=XSPEED;y+=YSPEED;break;
            case RD : x+=XSPEED;y+=YSPEED;break;
            case LU : x-=XSPEED;y-=YSPEED;break;
            case RU : x+=XSPEED;y-=YSPEED;break;
//            case STOP: break;
        }
//        if (dir != Direction.STOP)
            missileDir = dir;


        if (random.nextInt(10) > 8) this.fire();
    }

    /**
     * 坦克方向定位
     */
    private void positioning() {
        if (!bL && !bU && !bD && !bR)
            setMoving(false);
        else
            setMoving(true);

        if (bL && !bU && !bR && !bD )
            dir = Direction.L;
        else if (!bL && bU && !bR && !bD )
            dir = Direction.U;
        else if (!bL && !bU && bR && !bD )
            dir = Direction.R;
        else if (!bL && !bU && !bR && bD )
            dir = Direction.D;
        else if (bL && bU && !bR && !bD )
            dir = Direction.LU;
        else if (!bL && bU && bR && !bD )
            dir = Direction.RU;
        else if (!bL && !bU && bR && bD )
            dir = Direction.RD;
        else if (bL && !bU && !bR && bD )
            dir = Direction.LD;

    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public List<Missile> getMissileList() {
        return missileList;
    }

    /**
     *
     * @return 返回坦克所在的矩形
     */
    public Rectangle getRectangle(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
}
