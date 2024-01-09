package company_info;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class insert_dept {
    private DbUtil dbUtil = new DbUtil();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入部门号：");   //注意输入的部门号不要重复
        String dept_no = sc.nextLine();      //取得用户输入的字符串
        System.out.println("请输入部门名：");
        String dept_name = sc.nextLine();    //取得用户输入的字符串
        System.out.println("请输入部门地址：");
        String dept_loc = sc.nextLine();
        //float price = sc.nextFloat();
        insert_dept d = new insert_dept();
        d.inser_dept(dept_no, dept_name, dept_loc);
    }

    public void inser_dept(String dept_no, String dept_name, String dept_loc) {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            Statement stmt = con.createStatement();
            String sql = "insert into dept values ('" + dept_no + "','" + dept_name + "','" + dept_loc + "');";
            stmt.executeUpdate(sql);
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
