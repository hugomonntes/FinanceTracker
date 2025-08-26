
package financetracker;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import financetracker.DAO.UserDAO;
import financetracker.Models.User;

@DisplayName("Application")
public class ApplicationTest {
  private UserDAO userDAO;

  @Test
  void testCreateItem() throws SQLException {
    User newUser = new User(1, "hugo", "hmontes@gmail.com", "123");
    userDAO.createItem(newUser);
  }
}
