package com.liang.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceMgr {


    public static  BufferedImage tankD ;
    public static  BufferedImage tankL ;
    public static  BufferedImage tankR ;
    public static  BufferedImage tankU ;
    public static  BufferedImage tankLU ;
    public static  BufferedImage tankRU ;
    public static  BufferedImage tankLD ;
    public static  BufferedImage tankRD ;

    public static  BufferedImage MainTankD ;
    public static  BufferedImage MainTankU ;
    public static  BufferedImage MainTankL ;
    public static  BufferedImage MainTankR ;
    public static  BufferedImage MainTankLD ;
    public static  BufferedImage MainTankRD ;
    public static  BufferedImage MainTankLU ;
    public static  BufferedImage MainTankRU ;

    public static  BufferedImage bulletU ;
    public static  BufferedImage bulletD ;
    public static  BufferedImage bulletL ;
    public static  BufferedImage bulletR ;
    public static  BufferedImage missileLD;
    public static  BufferedImage missileRD;
    public static  BufferedImage missileLU;
    public static  BufferedImage missileRU;

    static {
        try {

            ClassLoader classLoader = ResourceMgr.class.getClassLoader();

            tankD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankD.gif")));
            tankL = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankL.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankR.gif")));
            tankU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankU.gif")));
            tankLU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankLU.gif")));
            tankRU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankRU.gif")));
            tankLD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankLD.gif")));
            tankRD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankRD.gif")));



            MainTankD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankDown.png")));
            MainTankU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankUp.png")));
            MainTankL = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankLeft.png")));
            MainTankR = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankRight.png")));
            MainTankLD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankLD.png")));
            MainTankRD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankRD.png")));
            MainTankLU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankLU.png")));
            MainTankRU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/MainTankRU.png")));


            bulletD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletD.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletU.gif")));
            bulletL = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletL.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletR.gif")));

            missileLD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/missileLD.gif")));
            missileLU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/missileLU.gif")));
            missileRD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/missileRD.gif")));
            missileRU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/missileRU.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
