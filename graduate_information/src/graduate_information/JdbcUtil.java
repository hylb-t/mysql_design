package graduate_information;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private static DataSource ds;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            connection = ds.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
