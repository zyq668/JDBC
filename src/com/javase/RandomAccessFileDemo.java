package com.javase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile用于对文件的读写
 */
public class RandomAccessFileDemo {
    /**
    对项目跟目录下一个名为demo.dat的文件进行读写
     每次用完IO要记得关闭
     void write(int n)
     写出给定int值的低8位
     只写一个字节
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("demo.dat","rw");
        int num = 255;
        //写
        raf.write(num);
        //raf.close();
        RandomAccessFile raf1 = new RandomAccessFile("demo.dat","r");
        //读  都是低8位
        int i = raf1.read();
        System.out.println(i);

        String str = "我带飞";
        byte[] buf = str.getBytes("UTF-8");
        raf.write(buf);
        raf.close();
    }

}
