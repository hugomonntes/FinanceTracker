/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
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

    public User getUniqueItem(int key) throws SQLException {
        Statement stmt = this.connection.client.createStatement();
        String query = "SELECT * FROM users WHERE id = " + key;
        ResultSet result = stmt.executeQuery(query);
        User user = new User();
        if (result.next()) {
            user = new User(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password_hash"));
        }

        stmt.close();
        return user;
    }

    public void createItem(User userToAdd) throws SQLException {
        Statement stmt = this.connection.client.createStatement();
        String query = String.format(
                "INSERT INTO users (id, username, email, password_hash) VALUES (%d, '%s', '%s', '%s')", userToAdd.getId(),
                userToAdd.getUsername(), userToAdd.getEmail(), userToAdd.getPassword());
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void updateItem(int key, User userToMod) throws SQLException {
        Statement stmt = this.connection.client.createStatement();
        String query = String.format(
                "UPDATE users SET username = '%s', email = '%s', password_hash = '%s' WHERE id = %d",
                userToMod.getUsername(), userToMod.getEmail(), userToMod.getPassword(), key);
        stmt.executeUpdate(query);
        stmt.close();
    }
}
