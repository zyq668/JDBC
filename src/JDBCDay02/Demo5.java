package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Demo5 {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "INSERT INTO Test VALUES(?,?,?,?,'中国')";
            //根据预编译sql创建一个prepareStatment
            PreparedStatement ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for(int i=100;i<110;i++){
                ps.setInt(1,i);
                ps.setString(2,"test"+i);
                ps.setInt(3,18);
                ps.setInt(4,1);
                ps.executeUpdate();
            }

            System.out.println("插入完毕");
            long end = System.currentTimeMillis();
            System.out.println("耗时："+(end - start));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
}
