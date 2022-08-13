package main.java.hiber.service;

import hiber.model.RaU;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteById(long id);
    void saveChange(User user);
    User getUserByUserName(String username);
    UserDetails fromUser (User user);
    List getRolesAndIdUsers();
    List <RaU> getIdRoleByIdUser(Long id);
}
