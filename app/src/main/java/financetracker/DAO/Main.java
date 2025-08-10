package financetracker.DAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDB cx = new ConnectionDB("jdbc:mysql://localhost:3306/FinanceTracker", "root", "");
        cx.initConexion();
    }
}
