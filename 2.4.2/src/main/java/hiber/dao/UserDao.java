package main.java.hiber.dao;

import hiber.model.RaU;
import hiber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteById(long id);
    void saveChange(User user);
    User getUserByUserName(String username);
    UserDetails fromUser (User user);
    List <RaU> getRolesAndIdUsers();
    List <RaU> getIdRoleByIdUser(Long id);

}
