package com.javase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 将Int 值写入文件
 * 01010111 11111111 11111111 10111111
 *每次只能写入低8位
 * num>>>24
 */
public class RandomAccessFileDemo3 {
    public static void main(String[] args) throws IOException {
        int num = Integer.MAX_VALUE;
        RandomAccessFile raf = new RandomAccessFile("demo.dat","rw");
    //    raf.write(num>>>24);
    //    raf.write(num>>>16);
    //    raf.write(num>>>8);

    //    raf.write(num);

        raf.writeInt(num);
        System.out.println("wan");
        raf.close();
    }
}
