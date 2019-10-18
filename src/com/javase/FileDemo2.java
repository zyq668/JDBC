package com.javase;

import java.io.File;
import java.io.FileFilter;

/**
 * listFiles方法支持文件过滤器
 * FileFilter接口
 * 实现该接口需要是西安抽象方法：
 * boolean accept(File f)
 * 该方法要求我们定义过滤条件
 * ListFiles方法会将当前目录下满足accept方法的子项返回
 */
public class FileDemo2 {
    public static void main(String[] args) {
        File dir = new File(".");
        if(dir.isDirectory()){
            //创建过滤器实例
            FileFilter filter = new MyFilter();
            //使用过滤器过滤子项
            File[] subs = dir.listFiles(filter);
            for(File sub : subs){
                System.out.println(sub.getName());
            }
        }
    }
}
class MyFilter implements FileFilter{

    @Override
    public boolean accept(File pathname) {
        //判断当前路径是否为.开头的
        System.out.println("正在过滤"+pathname.getName());
        return pathname.getName().startsWith(".");
    }
}
