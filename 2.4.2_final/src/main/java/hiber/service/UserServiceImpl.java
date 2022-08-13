package main.java.hiber.service;

import hiber.dao.UserDao;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user, String rolesNew) {
        userDao.add(user, rolesNew);
    }

    @Override
    public void deletePairUserRole(String s) {
        userDao.deletePairUserRole(s);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void saveChange(User user) throws Throwable {userDao.saveChange(user);}

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Role> getAllRoles(){return  userDao.getAllRoles();}

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List getRolesAndIdUsers() { return userDao.getRolesAndIdUsers();}

    @Override
    public Set<String> getIdRoleByIdUser(Long id) {
        return userDao.getIdRoleByIdUser(id);
    }

    @Override
    public UserDetails fromUser(User user) {
        return userDao.fromUser(user);
    }

    @Override
    public User getUserByUserName(String username) {
        List<User> users = userDao.getAllUsers();
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny();
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDao.getAllUsers();
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return fromUser(user.get());
    }
}
