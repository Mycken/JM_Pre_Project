package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String URL = "jdbc:mysql://localhost/mytestdb?useUnicode=true&" +
            "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "120247";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute( "CREATE TABLE `mytestdb`.`user` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(20) NULL," +
                    "`lastname` VARCHAR(20) NULL, `age` INT NULL,PRIMARY KEY (`id`))");
            System.out.println("Таблицв создана");
        } catch (SQLException e) {
            System.out.println("Такая таблица уже есть. Не переживай, идем дальше");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute( "DROP TABLE user");
            System.out.println("Таблицв удалена");
        } catch (SQLException e) {
            System.out.println("Даже не пытайся. Таблица отсутствует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute( "INSERT INTO user (name, lastname, age) values ('" + name + "', '" + lastName +"'," + age + ");");
            System.out.println("User  с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Расслабся. Добавление не удалось");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute( "DELETE FROM user WHERE id = " + id +";" );
            System.out.println("Запись удалена");
        } catch (SQLException e) {
            System.out.println("Разберись. Удалить не получилось");
        }
    }

    public List<User> getAllUsers() {

        List <User> allUser = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet result = null;
            try {
                result = statement.executeQuery("SELECT * FROM user ;");
                while (result.next()) {
                    User user = new User(result.getLong("id"),result.getString("name"),
                            result.getString("lastname"), result.getByte("age"));
                    allUser.add(user);
                }
            } finally {
                if (result != null) {
                    result.close();
                }
            }

        } catch (SQLException e) {
            System.out.println("Не выходят данные");
        } finally {
            return allUser;
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute( "DELETE FROM user;" );
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Очистить таблицу не получилось");
        }
    }
}
