package com.javase;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * int read(byte[] buf)
 * 一次尝试从文件中读取buf数组length个字节
 * 并从该数组的第一个位置处起存放实际读取到的字节
 * 返回值为实际读取到的字节数
 */
public class RandomAccessFileDemo2 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("demo.dat","rw");
        //想一次读50个字节
        byte[] buf = new byte[10];
        //实际读取的字节数
        //int len = raf.read(buf);
        //System.out.println(len);
        String str = new String(buf,"UTF-8");
        System.out.println(str.trim());

        /**
         * copy
         */
        RandomAccessFile raf1 = new RandomAccessFile("copy.dat","rw");
        int len1 = -1;
        while((len1 = raf.read(buf))!=-1){
            //从0开始，复制len个字节
            raf1.write(buf,0,len1);
        }
        raf1.close();
        raf.close();
    }
}
