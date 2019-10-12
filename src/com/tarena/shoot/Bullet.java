package com.tarena.shoot;

public class Bullet extends FlyingObject {
    private int speed = 3;
    //子弹坐标和英雄机位置相关
    public Bullet(int x,int y){
        image = ShootGame.bullet;
        width = image.getWidth();
        height = image.getHeight();
        this.x = x;
        this.y = y;

    }

    @Override
    public void step() {
        y -= speed;
    }

    @Override
    public boolean outofBounds() {
        return y < -height;
    }
}
