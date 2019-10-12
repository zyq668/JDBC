package com.tarena.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
    private BufferedImage[] images = {};
    private int index;
    private int doubleFire;
    private int life;
    public Hero(){
        image = ShootGame.hero0;
        width = image.getWidth();
        height = image.getHeight();
        //初始位置
        x = 180;
        y = 700;
        doubleFire = 0;
        life = 3;
        images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};

    }

    /**
     * 英雄机走步，切图片
     */
    @Override
    public void step() {
        image = images[index++/10%images.length];

    }

    @Override
    public boolean outofBounds() {
        return false;
        //永不出界
    }

    public Bullet[] shoot(){
        /**
         * 把英雄机分为4份，用于确定子弹位置
         * 单倍火力位于2/4，中间发射，双倍火力位于1/4，3/4
         * yStep，为了在英雄机的上方位置发射
         */
        int xStep = this.width/4;
        int yStep = 20;
        if(doubleFire > 0){
            //双倍火力
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(this.x+2*xStep,this.y-yStep);
            bullets[1] = new Bullet(this.x+3*xStep,this.y-yStep);
            return bullets;
        }else {
            //单倍火力
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(this.x+2*xStep,this.y-yStep);
            return bullets;
        }
    }
    /**
     * 移动 x,y:鼠标的x，y坐标
     * 使得英雄机中心位于鼠标位置
     */
    public void moveTo(int x,int y){
        this.x = x-this.width/2;
        this.y = y-this.height/2;
    }

    /**添加双倍火力*/
    public void addDoubleFire(){
        doubleFire += 20;
    }
    /**添加命*/
    public void addLife(){
        life++;
    }
    /**获取命*/
    public int getLife(){
        return life;
    }
    /**判断英雄机与敌人是否碰撞
     * other:敌人（敌机或蜜蜂）*/
    public boolean hit(FlyingObject other){
        int x1 = other.x - this.width/2;
        int x2 = other.x + other.width/2;
        int y1 = other.y - this.height/2;
        int y2 = other.y + other.height+this.height/2;
        int heroX = this.x + this.width/2;
        int heroY = this.y + this.height/2;
        return heroX > x1 && heroX < x2
                &&
                heroY >y1 && heroY < y2;
    }
    /**减命*/
    public void subtractLife(){
        life--;
    }
    /**设置火力*/
    public void setDoubleFire(int doubleFire){
        this.doubleFire = doubleFire;
    }
}
