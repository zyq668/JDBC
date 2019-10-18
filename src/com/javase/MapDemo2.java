package com.javase;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**遍历Map的三种方式
 * 1：遍历所有的key
 * 2：遍历所有的键值对（key-value）
 * 3：遍历所有的value（不常用）
 */
public class MapDemo2 {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("数学",90);
        map.put("英语",88);
        map.put("语文",77);
        map.put("物理",89);
        /*遍历所有的key
        Set<K> keySet();
        */
        Set<String> keyset = map.keySet();
        for(String key:keyset) {
            System.out.println("key:" + key);
        }
        /*
        遍历键值对
        Set<Entry>entrySet()
         */
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        for(Map.Entry<String,Integer> e:entrySet){
            String key =e.getKey();
            Integer value = e.getValue();
            System.out.println(key + value);
        }
        /*
        遍历所有的value
         */
        Collection<Integer> values = map.values();
        for(Integer value: values){
            System.out.println("value:"+value);
        }
    }
}
