package homework;

public class Fbi {
    public static void main(String[] args){
        for(int i=1;i<=20;i++){
            int nn=getFbi(i);
            if(nn<1000) {
                System.out.print(nn+ " ");
            }else {
                break;
            }
        }
        System.out.println("输出完毕");
    }
    private static int getFbi(int n){
        if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        }else{
            return getFbi(n-2)+getFbi(n-1);
        }
    }
}
