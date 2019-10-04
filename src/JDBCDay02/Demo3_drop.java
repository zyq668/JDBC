package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.Statement;

public class Demo3_drop {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "DELETE FROM Test WHERE id = 10";
            if(state.executeUpdate(sql)>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
