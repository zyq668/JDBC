package JDBCDay01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_day01 {
    public static void main(String[] args) {
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.100.162.15:3306/Mysql"
                    ,"Mysql","zhangyunqi668");
            Statement state = conn.createStatement();
            String sql = "SELECT * FROM emp";
            //执行sql，用结果集接收
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                System.out.println(id+"   "+username);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
