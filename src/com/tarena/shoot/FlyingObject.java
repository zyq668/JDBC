package com.tarena.shoot;
import java.awt.image.BufferedImage;
//飞行物类
public abstract class FlyingObject {
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected BufferedImage image;
    public abstract void step();
    public boolean shootBy(Bullet b){
        //子弹的x,y
        int x = b.x ;
        int y = b.y ;
        //子弹的x,y在飞行物的x,y加宽高之间
        return x > this.x && x < this.x + width
                &&
                y > this.y && y >this.y + height;
    }
    public abstract boolean outofBounds();

}
