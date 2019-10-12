package com.tarena.shoot;

import java.util.Random;

//小飞机,是飞行物，也是敌人
public class Airplane extends FlyingObject implements Enemy{
    private int speed = 2;
    public Airplane(){
     image = ShootGame.airplane;
     width = image.getWidth();
     height = image.getHeight();
     y = -height;
     Random rand = new Random();
     x = rand.nextInt(ShootGame.WIDTH - width);
    }


    @Override
    public int getScore() {
        return 5;
    }

    @Override
    public void step() {
        y+=speed;
    }

    @Override
    public boolean outofBounds() {
        return y > ShootGame.HEIGHT;
    }
}
