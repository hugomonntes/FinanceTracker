package financetracker.Models;

public class Main {
    public static void main(String[] args) {
        User us = new User();
        us.setUsername("Hola");
        us.setEmail("@gmail.com");
        us.setId(1);
        us.setPassword("1111");
        System.out.println(us.getEmail());
        System.out.println(us.getPassword());
    }
}
