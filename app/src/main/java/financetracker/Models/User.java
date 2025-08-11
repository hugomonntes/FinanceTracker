package financetracker.Models;

public class User {
    int id;
    String username;
    String email;
    String password_hash;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty or null");
        }
        String usernameFormated = username.toLowerCase().trim();
        for (int i = 0; i < usernameFormated.length(); i++) {
            if (!Character.isLetter(usernameFormated.charAt(i))) {
                throw new IllegalArgumentException("Username can only contain letters");
            }
        }
        this.username = usernameFormated;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        String regexp = "";
        this.email = email;
    }

    public User(int id, String username, String email, String password_hash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
    }
}
