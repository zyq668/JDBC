package com.javase;


import java.util.HashMap;
import java.util.Map;

/**
 * Map实现统计字符串中字符的个数
 * 1:创建map，用key存放字符，value存放字符个数
 * 2:遍历字符
 * 3:查看map中是否有对应的key
 * 4:没有则创建，有则更改值
 */
public class MapDemo3 {
    public static void main(String[] args) {
        String s = "good good study,day day up";
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,1);
            }else {
                map.put(c,map.get(c)+1);
            }
        }
        System.out.println(map);
    }
}
