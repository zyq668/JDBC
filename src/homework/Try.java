package homework;

public class Try {
    public static void main(String[] args) {
        //递归1+2+3+...+100
        long start = System.currentTimeMillis();
        int a=addnum(10000);
        System.out.println(a);
        long mul = System.currentTimeMillis();
        System.out.println(mul-start);
        int b = add2(10000);
        long end = System.currentTimeMillis();
        System.out.println(b);
        System.out.println(end-mul);
    }
    static int  sum=0;
    public static int addnum(int num){
        if(num!=0){
            sum+=num;
            num--;
            addnum(num);
        }
        return sum;
    }
    public static int add2(int n){
        if(n==1) {
            return 1;
        }else{
            return n+add2(n-1);
        }
    }
}
