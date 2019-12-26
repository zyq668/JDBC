package com.javase;

import java.io.*;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("pw.txt");
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while((str=br.readLine())!=null){
            System.out.println(str);
        }
        br.close();
    }
}
