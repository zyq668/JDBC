package JDBCDay02;

import JDBCDay01.DBUtil2;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.Statement;

public class Demo4 {
    /*
    创建表  java_Test
    字段：
    id           TINYINT(4)
    name         VARCHAR(20)
    password     VARCHAR(20)
    money        INT(10)
    email        VARCHAR(50)

    @author zhang
     */
    public static void main(String[] args) {
        try {
            /*
            CREATE TABLE java_Test(
            id TINYINT(4),
            name VARCHAR(20),
            password VARCHAR(20),
            money INT(10),
            email VARCHAR(50)
            );
             */
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "CREATE TABLE java_Test(" +
                    " id TINYINT(4)," +
                    " name VARCHAR(20)," +
                    " password VARCHAR(20)," +
                    " money INT(10)," +
                    " email VARCHAR(50)" +
                    " );";
            /*
            使用execute(String sql)执行DDL;
            execute方法可以执行任何sql语句
            返回值为true说明是查询语句，并返回了结果集
            通常我们使用它来执行DDL
             */
            boolean b = state.execute(sql);
            if(!b){
                System.out.println("表创建成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
}
