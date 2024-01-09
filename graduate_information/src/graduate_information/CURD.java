package graduate_information;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CURD {
    @Test
    public void testInsertPost() throws SQLException {
        String pno = "P00000003";
        String pname = "数据分析师";
        String wages = "12000";
        String pro = "数科";
        Connection connection = JdbcUtil.getConnection();
        String sql = "CALL insert_post1(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, pno);
        ps.setObject(2, pname);
        ps.setObject(3, wages);
        ps.setObject(4, pro);
        int count = ps.executeUpdate();
        System.out.println(count > 0);
        ps.close();
        JdbcUtil.freeConnection();
    }

    @Test
    public void testDeletePost() throws SQLException {
        String pno = "P00000003";
        Connection connection = JdbcUtil.getConnection();
        String sql = "CALL delete_post1(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, pno);
        int count = ps.executeUpdate();
        System.out.println(count > 0);
        ps.close();
        JdbcUtil.freeConnection();
    }

    @Test
    public void testInsert() throws SQLException {
        String cno = "B2107";
        String cname = "会计学院";
        String loc = "教学楼C";
        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into college(cno,cname,loc) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, cno);
        ps.setObject(2, cname);
        ps.setObject(3, loc);
        int count = ps.executeUpdate();
        //System.out.println(count > 0);
        ps.close();
        JdbcUtil.freeConnection();
    }

    @Test
    public void tsetUpdate() throws SQLException {
        String cno = "B2107";
        String cname = "教音学院";
        String loc = "教学楼D";
        Connection connection = JdbcUtil.getConnection();
        String sql = "update college set cname = ?,loc = ? where cno = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, cname);
        ps.setObject(2, loc);
        ps.setObject(3, cno);
        int count = ps.executeUpdate();
        System.out.println(count > 0);
        ps.close();
        JdbcUtil.freeConnection();
    }

    @Test
    public void testDelete() throws SQLException {
        String cno = "B2107";
        Connection connection = JdbcUtil.getConnection();
        String sql = "delete from college where cno = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, cno);
        int count = ps.executeUpdate();
        System.out.println(count > 0);
        ps.close();
        JdbcUtil.freeConnection();
    }

    @Test
    public void testSelect() throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from college;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        College college = null;
        List<College> colleges = new ArrayList<>();
        while (rs.next()) {
            String cno = rs.getString("cno");
            String cname = rs.getString("cname");
            String loc = rs.getString("loc");
            college = new College();
            college.setCno(cno);
            college.setCname(cname);
            college.setLoc(loc);
            colleges.add(college);
        }
        System.out.println(colleges);
        rs.close();
        ps.close();
        JdbcUtil.freeConnection();
    }
}
