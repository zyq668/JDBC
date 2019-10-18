package com.javase;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("数学",90);
        map.put("英语",88);
        map.put("语文",77);
        map.put("物理",89);
        Integer num = map.get("英语");
        System.out.println("英语:"+num);
        //int 会空指针异常
        Integer num1 = map.get("高数");
        System.out.println("高数:"+num1);
        if(map.containsKey("高数")){
            System.out.println("包含高数");
        }else {
            System.out.println("不包含高数");
        }
        System.out.println(map);
        map.remove("语文");
        System.out.println(map);
    }
}
