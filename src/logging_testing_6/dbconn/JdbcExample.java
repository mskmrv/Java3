package logging_testing_6.dbconn;

import java.sql.*;

public class JdbcExample {
    private static final String URL = "jdbc:sqlite:database.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readFromDB(String clientName) {
        String password = null;
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.name, p.password FROM users u INNER JOIN passwords p on u.id = p.id WHERE u.name = ?");
            preparedStatement.setString(1, clientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                String name = resultSet.getString("name");
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("returned password: " + password);
        return password;
    }

    public static void changeNick(String oldName, String newName) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE name = ?;");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);
            preparedStatement.executeUpdate();
            System.out.println("Name " + oldName + " changed to: " + newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
