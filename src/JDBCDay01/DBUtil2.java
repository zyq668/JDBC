package JDBCDay01;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
    //数据库连接池
    private static BasicDataSource ds;
    //为不同线程管理连接
    private static ThreadLocal<Connection> tl;
    static {
        try {
            Properties prop = new Properties();
            InputStream is = DBUtil2.class.getResourceAsStream(
                 "/JDBCDay01/config.properties"
            );
            prop.load(is);
            is.close();
            //初始化连接池
            ds = new BasicDataSource();
            //设置驱动（Class.forName()）
            //ds.setDriverClassName(prop.getProperty("driver").trim());
            //设置url
            ds.setUrl(prop.getProperty("url").trim());
            //设置用户名
            ds.setUsername(prop.getProperty("user").trim());
            //设置数据库密码
            ds.setPassword(prop.getProperty("pwd").trim());
            //最大连接数量
            ds.setMaxActive(Integer.parseInt(prop.getProperty("maxactive")));
            //初始的连接数
            ds.setInitialSize(Integer.parseInt(prop.getProperty("initsize")));
            //设置最大等待时间
            ds.setMaxWait(Integer.parseInt(prop.getProperty("maxwait")));
            //设置最小空闲数
            ds.setMinIdle(Integer.parseInt(prop.getProperty("minidle")));
            //设置最大空闲数
            ds.setMaxIdle(Integer.parseInt(prop.getProperty("maxidle")));
            //初始化线程本地
            tl = new ThreadLocal<Connection>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        //通过连接池获取一个空闲连接
        Connection conn =ds.getConnection();
        tl.set(conn);
        return conn;
    }
    public static void closeConnection(){
        try{
            Connection conn = tl.get();
            if(conn != null){
                /*
                这里注意
                    通过连接池获取的Connection的close()方法实际上并没有将
                    连接关闭，而是将连接归还给连接池
                 */
                conn.close();
                tl.remove();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
