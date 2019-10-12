package JDBCDay02;

import JDBCDay01.DBUtil2;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        System.out.println("请输入选项：");
        System.out.println("1: 注册新用户");
        System.out.println("2: 登陆用户");
        System.out.println("3：更改用户信息");
        System.out.println("4：注销用户");
        System.out.println("5：查询用户信息");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        switch (n){
            case 1:
                System.out.println("现在是注册操作");
                reguser(scan);
                break;
            case 2:
                System.out.println("现在是登陆操作");
                login(scan);
                break;
            case 3:
                System.out.println("现在是更改操作");
                change(scan);
                break;
            case 4:
                System.out.println("现在是注销操作");
                logoff(scan);
                break;
            case 5:
                System.out.println("现在是查询操作");
                select();
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
            Connection conn = DBUtil2.getConnection();
            String sql = "INSERT INTO java_test"+
                        " (`username`,`password`)"+
                        " VALUES"+
                        " (?,?)"
                    ;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
            System.out.println("创建成功！ 欢迎你 "+username);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
    //登陆用户
    public static int login(Scanner scan){
        try {
            System.out.println("请输入用户名");
            String username = scan.next();
            System.out.println("请输入密码");
            String password = scan.next();
            String sql = "SELECT * FROM java_test" +
                    " WHERE " +
                    " '"+username+"' = username " +
                    " AND" +
                    " '"+password+"' = password";
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if(rs.next()){
                int id = rs.getInt("id");
                System.out.println("登陆成功！ 欢迎你"+username);
                return id;
            }else {
                System.out.println("用户名或密码不正确！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
        return -1;
    }
    //更改用户信息
    public static void change(Scanner scan){
        try{
            System.out.println("更改信息请先登陆");
            int id =login(scan);
            if(id<0){
                System.out.println("请重新登陆/运行");
                System.exit(0);
            }
            System.out.println("请输入更改后的用户名");
            String username = scan.next();
            System.out.println("请输入更改后的密码");
            String password = scan.next();
            String sql = "UPDATE java_test" +
                        " SET `username` = '"+username+"' ,`password`='"+password+"'" +
                         " WHERE id = "+id+"";
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            if(state.executeUpdate(sql)>0){
                System.out.println("更改成功"+username);
            }else {
                System.out.println("更改失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
    //注销用户
    public static void logoff(Scanner scan){
        try {
            System.out.println("注销用户请先登陆");
            int id =login(scan);
            if(id<0){
                System.out.println("请重新登陆/运行");
                System.exit(0);
            }
            String sql = "DELETE FROM java_test" +
                        " WHERE" +
                        " id = "+id;
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            if(state.executeUpdate(sql)>0){
                System.out.println("注销成功！ 再见");
            }else {
                System.out.println("注销失败");
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally{
            DBUtil2.closeConnection();
        }
    }
    public static void select(){
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "SELECT * FROM java_test";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(id+"  "+username+"  "+password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
}
