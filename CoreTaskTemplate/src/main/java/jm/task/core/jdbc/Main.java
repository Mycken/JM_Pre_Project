package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
//        Создание таблицы User(ов)
//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//        Очистка таблицы User(ов)
//        Удаление таблицы
        UserServiceImpl usi = new UserServiceImpl();

        usi.createUsersTable();

        usi.saveUser("Gosha", "Dudar", (byte) 16);
        usi.saveUser("Vasja", "Petrov", (byte) 27);
        usi.saveUser("Dima", "Ivanov", (byte) 38);
        usi.saveUser("Sanja", "Kalov", (byte) 49);
        //usi.removeUserById(2);
        System.out.println(usi.getAllUsers().toString());
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
