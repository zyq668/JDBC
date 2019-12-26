package com.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    /**
     * Socket,用于连接服务端的ServerSocket
     */
    private Socket socket;

    /**
     * 客户端构造方法，用于初始化客户端
     */
    public Client() throws Exception {
        /**
         * 创建Socket对象时，就会尝试根据给定的地址与端口连接服务端
         * 所以，若该对象创建成功，说明与服务端连接正常
         */
        try {
            System.out.println("正在连接服务端...");
            socket = new Socket("localhost", 8088);
            System.out.println("成功连接服务端");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 客户端启动方法
     */
    public void start(){
        try{
            //创建并启动线程，来接受服务端的消息
            Runnable runn = new GetServerInfoHandler();
            Thread t = new Thread(runn);
            t.start();
            /**
             * 可以通过Socket的getOutputStream()方法
             * 获取一条输出流，用于将信息发送至服务端
             */
            OutputStream out = socket.getOutputStream();
            //使用字符流来根据指定编码集将字符串转换为字节，通过out发出
            OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
            //用缓冲流就可以 按照行为单位写出字符串了
            PrintWriter pw = new PrintWriter(osw,true);
            Scanner scan = new Scanner(System.in);
            //输出欢迎用语
            System.out.println("欢迎来到YQ聊天室");
            //做个简单的过滤
            while(true){
                //首先输入昵称
                System.out.println("请输入昵称:");
                String nickname = scan.next();
                if(nickname.trim().length()>0){
                    pw.println(nickname);
                    break;
                }else {
                    System.out.println("昵称不能为空");
                }
            }
            while(true){
                String str = scan.next();
                pw.println(str);
                //printwrite参数为true就不用flush了
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try{
            Client client = new Client();
            client.start();
        }catch (Exception e ){
            e.printStackTrace();
            System.out.println("客户端初始化失败");
        }
    }
    class GetServerInfoHandler implements Runnable{
        @Override
        public void run(){
            try{
                //获取输入流
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String str = null;
                while((str=br.readLine())!=null){
                    System.out.println("服务端说:"+str);
                }
            }catch (Exception e){}
        }
    }
}
