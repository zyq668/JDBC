package com.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //运行在服务端的Socket
    private ServerSocket server;
    //线程池，用于管理客户端连接的交换线程
    private ExecutorService threadPool;
    //保存所有客户端输出流的集合
    private List<PrintWriter> allout;
    /**
     * 构造方法，用于初始化服务器
     */
    public Server() throws IOException {
        try{
            System.out.println("初始化服务端");
            server = new ServerSocket(8088);
            //初始化线程池
            threadPool = Executors.newFixedThreadPool(5);
            //初始化所有客户端输出流集合
            allout = new ArrayList<PrintWriter>();
            System.out.println("初始化完毕");
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * 服务端开始工作的方法
     */
    public void start(){
        try{
            while(true) {
                System.out.println("等待客户端连接");
                Socket socket = server.accept();
                /**
                 * 当一个客户端连接后，启动一个线程
                 * ClientHandler,将该客户端的Socket传入，使得该线程
                 * 处理与该客户端的交互
                 * 便进入循环，再次等待下一个客户端连接
                 */
                Runnable handler = new ClientHandler(socket);
                //使用线程池分配空闲线程来处理当前连接的客户端
                threadPool.execute(handler);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将给定的输出流存入共享集合
     * @param pw
     */
    public synchronized void addOut(PrintWriter pw){
        allout.add(pw);
    }
    /**
     * 将给定的输出流从共享集合中删除
     * @param pw
     */
    public synchronized void removeOut(PrintWriter pw){
        allout.remove(pw);
    }
    /**
     * 将给定的消息转发给所有客户端
     * @param message
     */
    public synchronized void sendMessage(String message){
        for(PrintWriter pw :allout){
            pw.println(message);
        }
    }
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务端初始化失败");
        }
        /**
         * 服务端中的一个线程，用于与某个客户端交互
         * 使用线程的目的是使得服务端可以处理多客户端
         */
    }
    class ClientHandler implements Runnable{
        //当前线程处理的客户端的Socket
        private Socket socket;
        //当前客户端的IP
        private String ip;
        //当前客户端的昵称
        private String nickName;
        /**
         * 根据给定的客户端的Socket，创建线程体
         * @param socket
         */
        public ClientHandler(Socket socket){
            this.socket = socket;
            /**
             * 通过socket获取远端的地址信息
             * 对于服务端而言，远端就是客户端
             */
            InetAddress address = socket.getInetAddress();
            //获取远端ip地址
            ip= address.getHostAddress();
            //获取客户端的端口号
            int port = socket.getPort();
            System.out.println(ip+":"+port);
            System.out.println("客户端连上了");
            //通知其他用户，该用户上线了
            //改为使用昵称了，所以不在这里通知了
            //sendMessage("["+ip+"]上线了");
        }
        /**
         * 该线程会将当前Socket中的输入流获取用来循环读取客户端发来的信息
         */
        @Override
        public void run() {
            //定义在try外面目的是为了finally中可以引动到
            PrintWriter pw = null;
            try {
                /**
                 * 创建个输出流，使得服务端可向客户端发信息
                 */
                OutputStream ops = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(ops,"UTF-8");
                pw = new PrintWriter(osw,true);
                //输出流存入集合，便于该客户端接受服务端消息
                addOut(pw);
                //输出当前在线人数
                System.out.println("当前在线人数为:"+allout.size());
                /**
                 * 通过客户端的Socket获取输入流，
                 * 来读取客户端发送过来的信息
                 */
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                //将字符流转换为缓冲字符流，按行读取字符串
                BufferedReader br = new BufferedReader(isr);
                /**
                 * 当创建号当前客户端的输入流后，读取的第一个字符串应该是昵称
                 */
                nickName = br.readLine();
                //通知所有客户端，当前用户上线了
                sendMessage("["+nickName+"]上线了");
                String str = null;
                while ((str = br.readLine()) != null) {
                //    System.out.println("客户端说:" + str);
                //    pw.println(str);
                    /**
                     * 当读取到客户端发送过来的一条消息后，将该消息转发给所有客户端
                     */
                sendMessage(nickName+"说"+str);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //报错通常是因为客户端断开了连接
            } finally {
                /**
                 * 首先将该客户端的输出流在共享集合中删除
                 */
                //allout.remove(pw);
                removeOut(pw);
                //输出当前在线人数
                System.out.println("当前在线人数为:"+allout.size());
                //通知其他用户，该用户下线了
                sendMessage("["+ip+"]下线了");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("一个客户端下线了");
            }
        }
    }
}
