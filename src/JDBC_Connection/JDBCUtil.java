package JDBC_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection c = null;

        try {
            // Register the MySQL JDBC driver (deprecated method, use the new driver class for MySQL 8)
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // Database connection parameters

            String url = "jdbc:mysql://localhost:3306/Flower268?useSSL=false";

            // Create a connection
            c = DriverManager.getConnection(url, "root", "Link268");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static void closeConnection(Connection C){
        try {
            if(C != null) {
                C.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
