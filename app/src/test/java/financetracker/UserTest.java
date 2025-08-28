
package financetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
