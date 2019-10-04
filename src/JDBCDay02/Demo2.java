package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.Statement;

public class Demo2 {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            //执行更改数据库
            String sql = "UPDATE Test SET name='gai' WHERE id = 8;";
            System.out.println(sql);
            int flag = -1;
            flag = state.executeUpdate(sql);
            if(flag>0){
                System.out.println("更改成功");
            }else{
                System.out.println("更改失败");
            }
            DBUtil2.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
