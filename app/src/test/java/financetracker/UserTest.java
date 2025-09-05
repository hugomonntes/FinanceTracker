
package financetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import financetracker.DAO.ConnectionDB;
import financetracker.DAO.UserDAO;
import financetracker.Models.User;

@DisplayName("Application")
public class UserTest {
  @Test
  void testEquals(){
    User user1 = new User(1, "a", "b@gmail.com", "c");
    User user2 = new User(1, "a", "b@gmail.com", "c");
    User user3 = new User(3, "a", "b@gmail.com", "c");

    assertEquals(user1, user2);
    assertNotEquals(user2, user3);
    assertNotEquals(user1, user3);
  }

  @Test
  void testCheckPassword() throws SQLException {
    User fukin = new User();
    fukin.setPassword("aaa");
    assertTrue(fukin.checkPassword("$2a$10$XNXq6ikcBvkEEG0UK4u5hepLj7VdYxSU4QkK/G298FroxQf74Pf4O"), fukin.getPassword());
  }

}
