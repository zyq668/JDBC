package com.javase;

import java.io.File;
import java.io.IOException;

/**
 * 创建一个多级目录下的一个文件
 * 创建文件时，应首先判断其父目录存在不存在
 * 若不存在会抛出异常
 */
public class FileDemo3 {
    public static void main(String[] args) throws IOException {
        File file = new File("a"+ File.separator
                +"b"+File.separator
                +"c"+File.separator
                +"d"+File.separator
                +"e.txt"
                );
        File parent = file.getParentFile();
        if(!parent.exists()){
            parent.mkdirs();
        }
        if(!file.exists()){
            file.createNewFile();
            System.out.println("文件创建完毕");
        }
    }
}
