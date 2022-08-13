package main.java.hiber.dao;

import hiber.model.RaU;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void deleteById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void saveChange(User user) {
        User userToChange = entityManager.find(User.class, user.getId());
        userToChange.setFirstName(user.getFirstName());
        userToChange.setLastName(user.getLastName());
        userToChange.setEmail(user.getEmail());
        userToChange.setUsername(user.getUsername());
        userToChange.setPassword(user.getPassword());
        userToChange.setActive(user.isActive());

    }

    @Override
    public User getUserByUserName(String username) {
        return entityManager.find(User.class, username);
    }

    @Override
    public UserDetails fromUser(User user) {
          User userMod = entityManager.find(User.class, user.getId());

          return new org.springframework.security.core.userdetails.User(
          userMod.getUsername(), userMod.getPassword(),
          userMod.getAuthorities());
    }

    @Override

    public List <RaU>  getRolesAndIdUsers() {
        return entityManager.createQuery("from RaU").getResultList();
    }

    @Override
    public List<RaU> getIdRoleByIdUser(Long id) {
        Query query = entityManager.createQuery("from RaU r where r.id =: idP");
        query.setParameter("idP", id);
        List <RaU> roles =  query.getResultList();
        for (RaU r : roles){
            System.out.println(r.toString());
        }
        return roles;
    }
}
