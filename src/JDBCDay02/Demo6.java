package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Demo6 {
    /*
    取得结果集中的元数据
     */
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            String sql = "SELECT * FROM emp";
            //执行SELECT语句获取结果集
            ResultSet rs = state.executeQuery(sql);
            /*
            元数据在结果集中
            我们可以通过结果集获取元数据
             */
            ResultSetMetaData rsm = rs.getMetaData();
            //获取当前结果集查询的列总共多少个
            int columnCount = rsm.getColumnCount();
            for(int i=1;i<=columnCount;i++){
                //获取结果集中的第i列的列名
                String colName = rsm.getColumnName(i);
                System.out.println(colName);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil2.closeConnection();
        }
    }
}
