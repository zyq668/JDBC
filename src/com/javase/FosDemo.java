package com.javase;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileOutputStream
 * 节点流
 * 用于向文件中写出字节流
 */
public class FosDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.dat",true);
        fos.write(98);
        String str = "天安门上太阳升";
        byte[] buf = str.getBytes("UTF-8");
        fos.write(buf);
        fos.close();
    }
}
