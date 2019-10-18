package com.javase;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        File dir = new File(".");
        if(dir.isDirectory()){
            File[] subs = dir.listFiles();
            for(File sub : subs){
                String name = sub.getName();
                long length = sub.length();
                System.out.println(name+" "+length);
            }
        }
    }
    /**
     * 删除给定的多级目录
     * 递归
     * 2:不用for和while来实现1+2+3+...+100
     */
    public static void DeleteFile(File file){
        //判断它是不是目录
        if(file.isDirectory()){
            File[] subs = file.listFiles();
            for (File sub : subs ) {
                DeleteFile(sub);
            }
        }
        file.delete();
    }

}
