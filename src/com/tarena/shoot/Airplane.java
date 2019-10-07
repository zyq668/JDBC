package com.tarena.shoot;
//小飞机,是飞行物，也是敌人
public class Airplane extends FlyingObject implements Enemy{
    private int speed = 2;
    @Override
    public int getScore() {
        return 5;
    }
}
