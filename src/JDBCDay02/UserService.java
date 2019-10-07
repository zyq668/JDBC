package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.util.Scanner;

/*
与用户相关的业务逻辑
@author zhang
 */
public class UserService {
    public static void main(String[] args) {
        /*
        程序启动后：
        选择1,2,3,4,等操作
        1：注册新用户，用户id从1开始
        2：更改用户信息
        3：删除用户信息
        4：查询用户信息
         */
        int n = 0;
        System.out.println("请输入选项：");
        System.out.println("1: 注册新用户");
        System.out.println("2: 登陆用户");
        System.out.println("3：更改用户信息");
        System.out.println("4：删除用户信息");
        System.out.println("5：查询用户信息");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        switch (n){
            case 1:
                System.out.println("现在是注册操作");
                reguser(scan);
                break;
            case 2:
                System.out.println("现在是更改操作");
                break;
            case 3:
                System.out.println("现在是删除操作");
                break;
            case 4:
                System.out.println("现在是查询操作");
                break;
            default:
                System.out.println("输入错误");
        }

    }
    //注册用户
    public static void reguser(Scanner scan){
        try {
            System.out.println("请输入用户名:");
            String username = scan.next();
            System.out.println("请输入密码:");
            String password = scan.next();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //DBUtil2.closeConnection();
        }
    }
}
