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

package financetracker;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import financetracker.DAO.ConnectionDB;
import financetracker.DAO.UserDAO;
import financetracker.Models.User;

@DisplayName("Application")
public class DaoTest {
  static ConnectionDB connDB;
  static UserDAO userDAO;

  @BeforeAll
  public static void init() {
    connDB = new ConnectionDB("jdbc:mysql://localhost:3306/FinanceTracker", "root", "");
    connDB.initConexion();
    userDAO = new UserDAO(connDB);
  }

  @AfterAll
  public static void end() {
    connDB.closeConexion();
  }

  @BeforeEach
  public void cleanDB() throws SQLException {
    Statement stmt = connDB.client.createStatement();
    String query = "TRUNCATE TABLE users";
    stmt.executeUpdate(query);
    stmt.close();
  }

  @Test
  void testGetAllItems() throws SQLException {
    Statement stmt = connDB.client.createStatement();
    String query = String.format(
        "INSERT INTO users (id, username, email, password_hash) VALUES (%d, '%s', '%s', '%s'), (%d, '%s', '%s', '%s'), (%d, '%s', '%s', '%s')",
        1, "fukin", "f@asdfa.com", "pass", 2, "aaaa", "aaaaa@asdfa.com", "passsss", 41, "bbbbb", "bbbb@asdfa.com",
        "pddddass");
    stmt.executeUpdate(query);
    stmt.close();

    ArrayList<User> users = new ArrayList<>();
    users.add(new User(1, "fukin", "f@asdfa.com", "pass"));
    users.add(new User(2, "aaaa", "aaaaa@asdfa.com", "passsss"));
    users.add(new User(41, "bbbbb", "bbbb@asdfa.com", "pddddass"));

    ArrayList<User> usersToCompare = userDAO.getAllItems();

    for (User user : users) {
      boolean isFound = usersToCompare.contains(user);
      assertTrue(isFound, "Id: " + user.getId());
    }

    assertEquals(users.size(), usersToCompare.size());
  }

  @Test
  void testCreateItem() throws SQLException {
    ArrayList<User> usersToCompare = new ArrayList<>();
    usersToCompare.add(new User(42, "aaaaasdfaa", "asfdasfasf@asdfa.com", "pddddaasdfass"));
    userDAO.createItem(new User(42, "aaaaasdfaa", "asfdasfasf@asdfa.com", "pddddaasdfass"));
    assertArrayEquals(usersToCompare.toArray(), userDAO.getAllItems().toArray());
  }

  @Test
  void testUpdateItem() throws SQLException {
    Statement stmt = connDB.client.createStatement();
    String query = String.format(
        "INSERT INTO users (id, username, email, password_hash) VALUES (%d, '%s', '%s', '%s')",
        1, "fukin", "f@asdfa.com", "pass");
    stmt.executeUpdate(query);
    stmt.close();

    ArrayList<User> usersToCompare = new ArrayList<>();
    usersToCompare.add(new User(1, "fukin", "f@asdfa.com", "pass"));
    userDAO.updateItem(1, new User("fukinn", "f@asdfa.com", "pass"));
    for (User user : userDAO.getAllItems()) {
      boolean isSame = usersToCompare.contains(user);
      assertFalse(isSame);
    }
  }
}
