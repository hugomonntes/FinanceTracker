package financetracker.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import financetracker.Models.User;

public class UserDAO {
    ConnectionDB connection;

    public UserDAO(ConnectionDB connection) {
        this.connection = connection;
    }

    public ArrayList<User> getAllItems() throws SQLException {
        Statement stmt = this.connection.client.createStatement();
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            users.add(new User(result.getInt("id"), result.getString("username"), result.getString("email"),
                    result.getString("password_hash")));
        }

        result.close();
        stmt.close();
        return users;
    }

    public User getUniqueItem(int key) throws SQLException { //FIXME
        Statement stmt = this.connection.client.createStatement();
        String query = "SELECT * FROM users WHERE id = " + key;
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            
        }
        result.next();
        stmt.close();
        return new User(result.getInt("id"), result.getString("username"), result.getString("email"),
                result.getString("password_hash"));
    }
}
