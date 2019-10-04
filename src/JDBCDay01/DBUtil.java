package JDBCDay01;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    //保存地址，用户名，密码
    private static String url,user,pwd;
    //
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    //静态块
    //只加载一次，用于注册驱动等
    static {
        try {
            //读取配置文件
            Properties prop = new Properties();
            //建立流
            //获取DBUtil根目录（推荐这种写法的相对路径）
            InputStream is = DBUtil.class.getResourceAsStream
                    ("/JDBCDay01/config.properties");
            prop.load(is);
            //每次开启就要记得关
            is.close();
            //获取驱动
            String driver = prop.getProperty("driver");
            //获取地址
            url = prop.getProperty("url");
            //获取用户名
            user = prop.getProperty("user");
            //获取密码
            pwd = prop.getProperty("pwd");
            //注册驱动
            Class.forName(driver);
            //经过测试，驱动不写也会自动加载
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn =DriverManager.getConnection(url,user,pwd);
            /*
            ThreadLocal的set方法
            会将当前线程的key，并将给定的值作为value存入内部的map中。
             */
            tl.set(conn);
            return conn;
        }catch (Exception e){
            e.printStackTrace();
            //通知调用者，创建连接出错
            throw e;
        }
    }
    //提供全面，关闭工作流
    public static void closeConnection(){
        try {
            Connection conn = tl.get();
            if(conn != null){
                conn.close();
                //此时的Connection已经在map中没有作用了，清除掉即可
                tl.remove();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
