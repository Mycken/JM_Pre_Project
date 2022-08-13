package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl usi = new UserDaoJDBCImpl();
    //UserDaoHibernateImpl usi = new UserDaoHibernateImpl();

    public void createUsersTable() {

        usi.createUsersTable();
    }

    public void dropUsersTable() {
        usi.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usi.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
       usi.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return usi.getAllUsers();
    }

    public void cleanUsersTable() {
        usi.cleanUsersTable();
    }
}
