package homework;

public class print99 {
    public static void main(String[] args) {
        int i=1,j=1;
        for(;j<=9;){
            System.out.print(i+"×"+j+"="+i*j+" ");
            if(i==j){
                i=0;
                j++;
                System.out.println();
            }
            i++;
        }
    }
}
