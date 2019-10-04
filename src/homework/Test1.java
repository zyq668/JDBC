package homework;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test1 {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "SELECT * FROM emp";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                System.out.println("id:"+id+"  "+username);
            }
            DBUtil2.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
