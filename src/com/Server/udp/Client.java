package com.Server.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    /**
     * 客户端的启动方法
     */
    public void start(){
        try{
            /**
             * 向服务端发送数据的步骤
             * 1:创建好Socket
             * 2:准备数据
             * 3:创建数据包
             * 4:将数据存入包中
             * 5:将数据包通过socket发送给服务端
             */
            DatagramSocket socket = new DatagramSocket();
            String str = "你好!服务端!";
            byte[] data = str.getBytes("UTF-8");
            //打包:准备包裹，填写地址，装入数据
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8088;
            DatagramPacket sendPacket = new DatagramPacket(data,data.length,address,port);
            socket.send(sendPacket);

            //接受数据
            byte[] data1 = new byte[100];
            DatagramPacket recvPacket = new DatagramPacket(data1,data1.length);
            //接受数据到包中
            //该方法是个阻塞的方法
            socket.receive(recvPacket);
            //拆包拿数据
            byte[] d = recvPacket.getData();
            //有效数据长度
            int dlen = recvPacket.getLength();
            /**
             * String(byte[] b,int offset,int len,String charsetName)
             * 将给定的字节数组中，从offset处开始连续len个字节，
             * 再根据给定的字符集，转化为字符串
             */
            String info = new String(d,0,dlen,"UTF-8");
            System.out.println("服务端说:"+info);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
