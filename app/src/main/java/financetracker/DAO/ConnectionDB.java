package financetracker.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    String url;
    String user;
    String password;
    public Connection client;
    
    public ConnectionDB(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void initConexion() {
        if (client == null) {
            try {
                client = DriverManager.getConnection(url, user, password);
                System.err.println("Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopConexion() {
        try {
            client.close();
            System.err.println("Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
