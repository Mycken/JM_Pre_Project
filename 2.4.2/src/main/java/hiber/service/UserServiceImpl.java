package main.java.hiber.service;

import hiber.dao.UserDao;
import hiber.model.RaU;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void saveChange(User user) {
        userDao.saveChange(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }

    @Override
    public UserDetails fromUser(User user) {
        return userDao.fromUser(user);
    }

    @Override
    public List getRolesAndIdUsers() {
        return userDao.getRolesAndIdUsers();
    }

    @Override
    public List<RaU> getIdRoleByIdUser(Long id) {
        return userDao.getIdRoleByIdUser(id);
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
