package com.javase;

/**
 * 同步锁
 */
public class SyncDemo {
    public static void main(String[] args) {
        SyncDemo syn = new SyncDemo();
        Thread t1 = new Thread(){
            @Override
            public void run(){
                syn.buy(getName());
            }
    };
        Thread t2 = new Thread(){
            @Override
            public void run(){
                syn.buy(getName());
            }
        };
        t1.start();
        t2.start();
    }
    public void buy(String name) {
        System.out.println(name+"正在挑衣服");
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(name+"挑完衣服了");
        synchronized (this){
            System.out.println(name+"去试衣服");
            try{
            Thread.sleep(3000);
            }catch (InterruptedException e){
            e.printStackTrace();
            }
        System.out.println(name+"结账");
        }
    }
}
