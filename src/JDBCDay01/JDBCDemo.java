package JDBCDay01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnnection();
            System.out.println("连接成功");
            Statement state = conn.createStatement();
            String sql = "SELECT * FROM emp";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                System.out.println("id:" + id + "  " + "username:" + username);
            }
            /*
            当不在通过statement执行其他sql时，
            应当及时关闭statement，以释放JDBC与数据库的资源占用。
             */
            state.close();
            //使用后关闭连接
            DBUtil.closeConnecion();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
