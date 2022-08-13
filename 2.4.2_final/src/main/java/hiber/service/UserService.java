package main.java.hiber.service;

import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface UserService {
    void add(User user, String rolesNew);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteById(long id);
    void saveChange(User user) throws Throwable;
    User getUserByUserName(String username);
    UserDetails fromUser (User user);
    List getRolesAndIdUsers();
    Set<String> getIdRoleByIdUser(Long id);
    List<Role> getAllRoles();
    void deletePairUserRole(String s);


}
