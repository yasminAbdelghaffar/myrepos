package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static DBconnection instance;

    public static DBconnection getInstance() {
        if (instance == null) {
            instance = new DBconnection();
        }
        return instance;
    }

    public Connection connection = null;

    public Connection getConnection() {

        System.out.println("Mysql Testing");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/product", "root", "");

        } catch (Exception ex) {

            System.out.println("Connection Error");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Connection Success");
            } else {
                System.out.println("Connection Failed");
            }

        }
        return connection;
    }
}
