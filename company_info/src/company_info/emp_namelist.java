package company_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class emp_namelist {
    public static void main(String[] args) {
        emp_namelist el = new emp_namelist();
        el.ser_emp();
    }

    private DbUtil dbUtil = new DbUtil();

    public void ser_emp() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbUtil.getCon();
            String sql = "SELECT empno,ename,job FROM emp";
            // 获取执行sql语句对象
            pstmt = con.prepareStatement(sql);
            // 执行查询操作
            rs = pstmt.executeQuery();
            //处理结果集
            int flag = 1;
            while (rs.next()) {
                if (flag == 1) {
                    System.out.println("empno" + "\t" + "ename" + "\t" + "job");
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                    flag = 0;
                } else {
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
