package financetracker.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String url;
    String user;
    String password;
    public Connection client;
    
    public Conexion(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void initConexion() throws SQLException{
        if (client == null) {
            client = DriverManager.getConnection(url, user, password);
            System.err.println("Connected");
        }
    }

    public void stopConexion() throws SQLException{
        client.close();
        System.err.println("Disconnected");
    }
}
