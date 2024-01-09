package graduate_information;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDao {
    /**
     * 通用的增、删、改的方法
     * @param sql sql语句
     * @param args 给sql中的？设置的值列表, 可以是0~n
     * @return
     */
    public int update(String sql,Object... args) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
        }
        int len = ps.executeUpdate();
        ps.close();
        if (connection.getAutoCommit()) {
            JdbcUtil.freeConnection();
        }
        return len;
    }

    public <T>ArrayList<T> query(Class<T> clazz,String sql,Object... args) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
        }
        ArrayList<T> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                //for循环一次, 代表着取某一行的1个单元格的值
                Object value = rs.getObject(i);
                String columnName = metaData.getColumnLabel(i);
                Field filed = clazz.getDeclaredField(columnName);
                filed.setAccessible(true);
                filed.set(t,value);
            }
            list.add(t);
        }
        rs.close();
        ps.close();
        if (connection.getAutoCommit()) {
            JdbcUtil.freeConnection();
        }
        return list;
    }

    public <T> T queryBean(Class<T> clazz,String sql,Object... args) throws Exception {
        ArrayList<T> list = query(clazz,sql,args);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
