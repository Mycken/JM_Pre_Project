package main.java.hiber.dao;

import hiber.model.RaU;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void add(User user, String rolesNew);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteById(long id);
    boolean saveChange(User user) throws Throwable;

    UserDetails fromUser (User user);
    List <RaU> getRolesAndIdUsers();
    Set<String> getIdRoleByIdUser(Long id);
    List <Role> getAllRoles();
    Role getRoleById(String role);

    void deletePairUserRole(String s);
}
