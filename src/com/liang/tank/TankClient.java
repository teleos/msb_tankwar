package com.liang.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TankClient extends Frame {


    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    Tank myTank ;

    List<Tank> enemyTanks = new CopyOnWriteArrayList<>();
    private Image offScreenImage = null;
    private List<Explode> explodes = new CopyOnWriteArrayList<>();
    public TankClient(){
        init();
    }


    /**
     * 客戶端初始化方法
     */
    private void init(){

        myTank = new Tank(50,50,true,this);
        //初始化enemyTanks
        for (int i = 0; i< 10 ; i ++){
          enemyTanks.add( new Tank( 60+i*60 ,400,false,this))  ;
        }

        setTitle("坦克大戰！");
        setBounds(new Rectangle(0,0, GAME_WIDTH, GAME_HEIGHT));
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setBackground(Color.GREEN);
        setResizable(false);
        setLocation(100,100);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myTank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                myTank.keyReleased(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                myTank.keyTyped(e);
            }
        });

        List<Missile> missiles = myTank.getMissileList();

        new Thread( ()->{
            while (true){
                //獲取子彈
                repaint();
                //TODO 子弹击中坦克
                missiles.forEach(missile -> enemyTanks.forEach(
                        enemyTank->{
                            if(enemyTank.isAlive()  &&  missile.hitTank(enemyTank)){
//                                System.out.println("hit success！");
                            }


                            List<Missile> missiles1 = enemyTank.getMissileList();
                            missiles1.forEach( missile1 -> {
                                if(myTank.isAlive()  &&  missile.hitTank(myTank)){
//                                System.out.println("hit success！");
                                }

                            });

                        }
                ));
                System.out.println("坦克個數："+enemyTanks.size()+"\t子彈個數："+missiles.size());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        } ).start();
    }


    /**
     * 解決掉幀問題
     */
    @Override
    public void update(Graphics g){
        if (offScreenImage == null)
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);

        Graphics offScreenGraphics = offScreenImage.getGraphics();
        Color color = offScreenGraphics.getColor();
        offScreenGraphics.setColor(Color.GREEN);
        offScreenGraphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        offScreenGraphics.setColor(color);
        print(offScreenGraphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
      myTank.draw(g);
      enemyTanks.forEach(enemyTank -> enemyTank.draw(g));

    /*  explodes.forEach( explode ->{
          if (explode.isAlive())
              explode.draw(g);
          else
              explodes.remove(explode);
      } );*/

        for (int i = 0; i < explodes.size(); i++) {
            Explode  explode = explodes.get(i);
            if (explode.isAlive())
                explode.draw(g);
            else
                explodes.remove(explode);
        }


      enemyTanks.removeIf( (tank) ->   !tank.isAlive()  );

      g.drawString("explodes count :" + explodes.size(),10,70);
    }
    public List<Explode> getExplodes() {
        return explodes;
    }
    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    public static void main(String[] args) {
        new TankClient();
    }


}
