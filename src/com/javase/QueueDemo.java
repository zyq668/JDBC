package com.javase;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        /*
        boolean offer(T t)  入队元素到末尾，插入成功返回true
         */
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        System.out.println(queue);
        /*
        T poll()
        用于获取队首元素，出队操作
         */
        String str = queue.poll();
        System.out.println(str);
        System.out.println(queue);
        /*
        T peek()
        获取队首元素，仅引用，不做出队操作
         */
        str = queue.peek();
        System.out.println(str);
        System.out.println(queue);
    }
}
