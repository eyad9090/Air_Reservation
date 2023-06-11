package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * This Class Just For Connect Project To The Local DataBase
 *
 * @author Anwar
 */
public class Connect {
    /**
     * @param url For URL of DataBase
     * @param name For name DataBase's Admin
     * @param pass For Password of DataBase
     */
    private final String url;
    private final String name;
    private final String pass;

    public Connect()
    {
        this.url="jdbc:sqlserver://DESKTOP-GEIR0VT;databaseName=ARS;";
        this.name="Anwer";
        this.pass="123456";
    }
    public Connection connection()
    {
        /**
         * This Method For Call Constructor and Fill Data Of Connection
         */
        try {
            return DriverManager.getConnection(url,name,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

