
package financetracker;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

  // Devuelve un array con los usuarios que hay en la base de datos
  // Tipos de comprobación
  // 1º Coinciden el numero de elementos?
  // 2º
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
      userDAO.createItem(new User());
      
      
  }

  @Test
  void testCheckPassword() {
    User fukin = new User();
    fukin.setPassword("123");
    assertTrue(fukin.checkPassword(fukin.getPassword()));
  }
}
