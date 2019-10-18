package com.javase;

import java.util.Deque;
import java.util.LinkedList;

public class StackDemo {
    public static void main(String[] args) {
        Deque<String> stack = new LinkedList<String>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        System.out.println(stack);
        String str = stack.pop();
        System.out.println(str);
        System.out.println(stack);
        str = stack.peek();
        System.out.println(str);
        System.out.println(stack);
        //遍历栈操作
        while(stack.size()>0){
            str = stack.pop();
            System.out.println(str);
        }
        System.out.println(stack);
    }
}
