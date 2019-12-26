package com.javase;

/**
 * 多线程同步协作
 */
public class ThreadDemo {
    private static Object obj = new Object();
    private static boolean Isfinish = false;
    public static void main(String[] args) {
        Thread download = new Thread(){
            @Override
            public void run(){
                download();
            }
        };
        Thread show = new Thread(){
            @Override
            public void run(){
                try {
                    //download.join();
                    synchronized (obj){
                        obj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!Isfinish){
                    throw new RuntimeException();
                }
                show();
            }
        };
        download.start();
        show.start();
    }
    public static void download(){
        System.out.println("开始加载资源");
        for (int i = 1; i <= 100; i++) {
            System.out.println("资源已加载"+i+"%");
            try {
                Thread.sleep(30);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Isfinish = true;
        synchronized (obj) {
            obj.notify();
        }
        System.out.println("开始加载附件");
        for (int i = 1; i <= 100; i++) {
            System.out.println("附件已加载"+i+"%");
            try{
                Thread.sleep(30);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }
        System.out.println("全部加在完成");
    }
    public static void show(){
        System.out.println("显示信息");

    }
}
