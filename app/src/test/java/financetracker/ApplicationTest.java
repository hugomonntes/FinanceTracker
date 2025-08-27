
package financetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import financetracker.DAO.ConnectionDB;
import financetracker.DAO.UserDAO;
import financetracker.Models.User;

@DisplayName("Application")
public class ApplicationTest {
  ConnectionDB connDB = new ConnectionDB("jdbc:mysql://localhost:3306/FinanceTracker", "root", "");
  User newUser = new User(1, "hugo", "hmontes@gmail.com", "123");
  UserDAO userDAO;

  @Test
  void testCreateItem() throws SQLException {
    connDB.initConexion();
    userDAO = new UserDAO(connDB);
    userDAO.createItem(newUser);

    User userToCompare = userDAO.getUniqueItem(1);
    assertEquals("hugo", userToCompare.getUsername());
  }

  @Test
  void testCheckPassword(){ // FIXME fix password hash login, user put her password and this is not equals than the bd pass
    assertTrue(newUser.checkLogin(newUser.getEmail(), newUser.getPassword()));
  }
}
