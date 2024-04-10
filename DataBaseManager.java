package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private Connection dbConnect;
    private static final String DB_URL = "jdbc:postgresql://localhost/ensf380project";
//    private static final String USER = "oop"; // Update with your PostgreSQL username
    private static final String USER = "postgres"; // Update with your PostgreSQL username

    private static final String PASS = "14286502"; // Update with your PostgreSQL password

    /**
     * Constructor to connect to the PostgreSQL database.
     */
    public DataBaseManager() {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            // Connect to the PostgreSQL database
            dbConnect = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the database connection.
     *
     * @return Connection object for the database.
     */
    public Connection getConnection() {
        return this.dbConnect;
    }

    /**
     * Close the database connection.
     */
    public void closeConnection() {
        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                dbConnect.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
