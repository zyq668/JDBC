package JDBCDay02;

import JDBCDay01.DBUtil2;

import java.sql.Connection;
import java.sql.Statement;

public class Demo1 {
    public static void main(String[] args) {
        try{
            Connection conn = DBUtil2.getConnection();
            Statement state = conn.createStatement();
            /*
            INSERT INTO Test(id,name,age,sex,addr)
            插入数据
             */
            String sql = "INSERT INTO Test"+
                    " (id,name,age,sex,addr)"+
                    " VALUES"+
                    " (8,'ba',8,1,'上海');";
            System.out.println(sql);
            int flag = state.executeUpdate(sql);
            if(flag>0){
                System.out.println("插入成功");
            }else {
                System.out.println("插入失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
