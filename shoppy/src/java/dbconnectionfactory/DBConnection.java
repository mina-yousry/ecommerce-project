package dbconnectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this class is responsible of returning a database connection object 
 *
 * @author Mina
 */
public class DBConnection {

    public static final String URL = "jdbc:mysql://127.0.0.1:6666/";
    public static final String USER = "root";
    public static final String PASS = "1234";

    /**
     * public constructor used to get a connection and return it.
     *
     * @return connection object to the database
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(URL + "ecommerce", USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("RuntimeExceptionError connecting to the database", ex);
        }
    }
}
