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
package financetracker.Models;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    int id;
    String username;
    String email;
    String password;

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
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty or null");
        }
        boolean regexp = email.matches("^[a-zA-Z0-9._]+@[a-z]+\\.[a-z]{2,}$");
        if (!regexp) {
            throw new IllegalArgumentException("Email cannot be correct");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = passHash;
    }

    public String getPassword() {
        return password;
    }

    public User(int id, String username, String email, String password) {
        setId(id);
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public User(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public User() {
    }

    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email or password has been denied!");
        }
        return BCrypt.checkpw(password, this.getPassword());
    }

    @Override
    public boolean equals(Object obj) { // TODO Fix when pass hash is fixed add to equals check
        if (obj instanceof User) {
            User objAux = (User) obj;
            if (this.getId() == objAux.getId() && this.getUsername().equals(objAux.getUsername())
                    && this.getEmail().equals(objAux.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
