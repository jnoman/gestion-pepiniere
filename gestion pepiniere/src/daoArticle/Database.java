package daoArticle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	private String url;
    private String username;
    private String password;

    Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static Database getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        Database instance = new Database(
                "jdbc:mysql://localhost:3306/gestion_pepiniere", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion; 
    }

    public InterfaceArticle getInterfaceArticle() {
        return new DatabaseConnectionArticle(this);
    }
}
