package homework;

import java.util.Random;

public class Try {
    public static void main(String[] args) {
        int[] array = new int[6];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(9);
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
        //选择排序
        int flag = 0 ;
        int t = 0 ;
        for (int i = 0; i < array.length; i++) {
            flag = i;
            for (int j = i + 1; j < array.length; j++) {
                if(array[flag]<array[j]){
                    flag = j;
                }
            }
            if(flag!=i) {
                t = array[flag];
                array[flag] = array[i];
                array[i] = t;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
}
