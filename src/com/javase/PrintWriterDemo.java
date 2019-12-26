package com.javase;

import java.io.*;

/**
 * @author asus
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fos = new FileOutputStream("pw.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        PrintWriter pw = new PrintWriter(osw);
        pw.println("asd");
        pw.println("时尚");
        pw.close();
    }
}
