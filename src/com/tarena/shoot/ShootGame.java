package com.tarena.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ShootGame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage gameover;
    public static BufferedImage pause;
    public static BufferedImage Bee;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bullet;
    public static BufferedImage airplane;
    static {//加载图片资源
        try {
            background = ImageIO.read(ShootGame.class.getResource("background.png"));
            start = ImageIO.read(ShootGame.class.getResource("start.png"));
            gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
            pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
            Bee = ImageIO.read(ShootGame.class.getResource("Bee.png"));
            hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
            hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
            bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
            airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
