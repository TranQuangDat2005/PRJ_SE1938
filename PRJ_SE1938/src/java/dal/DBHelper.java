package dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daizl
 */
public class DBHelper implements Serializable {

    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String username = "admin";
    private static final String password = "3))%@))5";
    private static final String database = "FlashcardDB";

    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        //1. Load Driver
        Class.forName(driver);
        //2. Create Connection String
        String url = "jdbc:sqlserver:"
                + "//localhost:1433"
                + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true";
        //3. Open Connection
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
