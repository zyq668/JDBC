package JDBCDay01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_day01 {
    public static void main(String[] args) {
        try{
            //加载配置文件
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(
                    "./src/JDBCDay01/config.properties"
            );
            prop.load(fis);
            fis.close();       //注意关闭流
            System.out.println("成功加载配置文件");
            //可以根据key获取value内容
             //变相的把properties看作一个Map
            String driver = prop.getProperty("driver").trim();  //trim(()方法去除空白
            String url = prop.getProperty("url").trim();
            String user = prop.getProperty("user").trim();
            String pwd = prop.getProperty("pwd");
            System.out.println("driver"+driver);
            System.out.println("url"+url);
            System.out.println("user"+user);
            System.out.println("pwd"+pwd);


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
