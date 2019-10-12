package com.tarena.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author asus
 */
public class ShootGame extends JPanel{
    public static final int WIDTH = 524;
    public static final int HEIGHT = 928;
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage gameover;
    public static BufferedImage pause;
    public static BufferedImage Bee;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bullet;
    public static BufferedImage airplane;
    public Hero hero = new Hero();
    public Bullet[] bullets = {};
    public FlyingObject[] flyings = {};

    public ShootGame(){
        flyings = new FlyingObject[2];
        flyings[0] = new Airplane();
        flyings[1] = new Bee();
        bullets = new Bullet[1];
        bullets[0] = new Bullet(200,350);
    }
    /**设置状态*/
    private int state;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    /**加载图片资源*/
    static {
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
    //重写绘制方法

    @Override
    public void paint(Graphics g){
        g.drawImage(background,0,0,null);
        paintHero(g);
        paintBullets(g);
        paintFlyingObjects(g);
        //画分数
        paintScore(g);
        //画游戏状态
        paintState(g);
    }
    public void paintState(Graphics g){
        switch(state){
            case START:
                g.drawImage(start,0,0,null);
                break;
            case PAUSE:
                g.drawImage(pause,0,0,null);
                break;
            case GAME_OVER:
                g.drawImage(gameover,0,0,null);
                break;
            default:
                break;
        }
    }
    public void paintHero(Graphics g){
        g.drawImage(hero.image,hero.x,hero.y,null);
    }
    public void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            g.drawImage(b.image,b.x,b.y,null);
        }
    }
    public void paintFlyingObjects(Graphics g){
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            g.drawImage(f.image,f.x,f.y,null);
        }
    }
    //画分和命
    public void paintScore(Graphics g){
        int x = 20;
        int y = 25;
        //字体，加粗，字号
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        //16进制红 绿 蓝
        g.setColor(new Color(0x0000FF));
        g.drawString("SCORE:"+score,x,y);
        g.drawString("LIFE:"+hero.getLife(),x,y+20);
    }
    private ScheduledExecutorService timer;
    /**时间间隔（毫秒）*/
    private int interval = 10;
    /**启动执行操作*/
    public void action(){
        //鼠标事件适配器
        MouseAdapter l = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.moveTo(x, y);
                }
            }
            //鼠标点击
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state){
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        hero = new Hero();
                        flyings = new FlyingObject[0];
                        bullets = new Bullet[0];
                        score = 0;
                        state = START;
                        break;
                    default:
                        break;
                }
            }
            //鼠标移出
            @Override
            public void mouseExited(MouseEvent e) {
                if(state != GAME_OVER){
                    state = PAUSE;
                }
            }
            //鼠标移入
            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE){
                    state = RUNNING;
                }
            }
        };
        //给当前面板添加鼠标点击侦听
         this.addMouseListener(l);
        //给当前面板添加鼠标滑动侦听
        this.addMouseMotionListener(l);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == RUNNING) {
                    enterAction();
                    //飞行物走步
                    stepAction();
                    //定时的发射子弹
                    shootAction();
                    //子弹打敌人
                    bangAction();
                    //删除越界的飞行物
                    outOfBoundsAction();
                    //判断是否游戏结束
                    checkGameOverAction();
                    //重新绘画页面
                    repaint();
                }
            }
        },interval,interval);
    }
    //飞行物入场计数
    int flyEnteredIndex = 0;
    public void enterAction(){
        /*飞行物入场*/
        flyEnteredIndex++;
        //走20次出一只
        if(flyEnteredIndex % 40 ==0){
            FlyingObject obj = nextOne();
            flyings = Arrays.copyOf(flyings,flyings.length+1);
            flyings[flyings.length-1] = obj;
        }
    }
    public static FlyingObject nextOne(){
        Random rand = new Random();
        int type = rand.nextInt(20);
        if(type == 0){
            return new Bee();
        }else {
            return new Airplane();
        }
    }
    public void stepAction(){
        //所有敌机和小蜜蜂走一步
        for (int i = 0; i < flyings.length; i++) {
            flyings[i].step();
        }
        //所有子弹走一步
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].step();
        }
        hero.step();
    }
    //控制射击频率
    int shootIndex = 0;
    /** 射击*/
    public void shootAction(){
        shootIndex++;
        //300毫秒发一次,由于定时器是10毫秒触发一次
        if(shootIndex % 30==0){
            Bullet[] bs = hero.shoot();
            //数组扩容
            bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
            //数组追加
            //从bs的第0个元素，放在bullets中的第bullets.length-bs.length位置，放入bs.length个。
            System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
        }
    }
    public void bangAction(){
        for (int i = 0; i < bullets.length; i++) {
            //每一个子弹
            Bullet b = bullets[i];
            bang(b);
        }
    }
    /**删除越界飞行物
     * 遍历飞行物，将活着的飞行物存起来
     * */
    public void outOfBoundsAction(){
        int index = 0;
        FlyingObject[] flyingLives = new FlyingObject[flyings.length];
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            //若不出界
            if(!f.outofBounds()){
                flyingLives[index++] = f;
            }
        }
        //index++很好实现了角标和数量
        flyings = Arrays.copyOf(flyingLives,index);

    //删除所有出界的子弹
    index = 0;
    Bullet[] bulletLives = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            if(!b.outofBounds()){
                bulletLives[index++] = b;
            }
        }
    }
    //记录分数
    private int score =0;
    /**子弹打敌人*/
    public void bang(Bullet b){
        //记录击中的下标
        int index = -1;
        for(int i = 0;i<flyings.length;i++){
            FlyingObject obj = flyings[i];
            if(obj.shootBy(b)){
                index = i;
                break;
            }
        }
        //有击中的敌人
        if(index != -1){
            //获取击中的目标
            FlyingObject one = flyings[index];
            //删除 被击中的飞行物,先交换到末尾再切掉
            FlyingObject t = flyings[index];
            flyings[index] = flyings[flyings.length-1];
            flyings[flyings.length-1] = t;
            //删除数组中的最后一个元素
            flyings = Arrays.copyOf(flyings,flyings.length-1);
            //判断是敌人还是奖励
            if(one instanceof Enemy){
                //飞行物强转敌人
                Enemy e = (Enemy)one;
                //设置加分
                score += e.getScore();
            }else if(one instanceof Award){
                //飞行物强转奖励
                Award a = (Award) one;
                //得到奖励类型
                int type = a.getType();
                switch(type){
                    case Award.DOUBLE_FIRE:
                        hero.addDoubleFire();
                        break;
                    case Award.LIFE:
                        hero.addLife();
                        break;
                    default:
                        System.out.println("非奖励");
                        break;
                }
            }
        }
    }
    /**检查游戏是否结束*/
    public void checkGameOverAction(){
        if(isGameOver()){
            state = GAME_OVER;
        }
    }
    /**判断游戏是否结束*/
    public boolean isGameOver(){
        for (int i = 0; i < flyings.length; i++) {
            //记录撞上飞行物索引
            int index = -1;
            FlyingObject obj = flyings[i];
            if(hero.hit(obj)){
                //减命
                hero.subtractLife();
                //设置火力归0
                hero.setDoubleFire(0);
                //记录索引，哪个被撞,要去删掉
                index = i;
            }
            if(index != -1){
                //交换到末尾
                FlyingObject t = flyings[index];
                flyings[index] = flyings[flyings.length-1];
                flyings[flyings.length-1] = t;
                //缩容
                flyings = Arrays.copyOf(flyings,flyings.length-1);
            }
        }
        //返回是否有命
        return hero.getLife()<=0;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fly");
        ShootGame game = new ShootGame();
        frame.add(game);
        frame.setSize(WIDTH,HEIGHT);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.action();
    }

}
