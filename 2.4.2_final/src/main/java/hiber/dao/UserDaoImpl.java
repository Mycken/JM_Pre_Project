package main.java.hiber.dao;

import hiber.model.RaU;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user, String rolesNew) {
          entityManager.persist(user);
          String Key = user.getId().toString() + "_" + rolesNew;
          RaU raU = new RaU(user.getId(), rolesNew, Key);
          entityManager.persist(raU);
    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = entityManager.createQuery("from User").getResultList();
        for (User u:users){
            System.out.println(u.getId() + "  " + u.getAuthorities());
        }
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public List<Role> getAllRoles(){
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role getRoleById(String role) { return entityManager.find(Role.class,role);}

    @Override
    public void deletePairUserRole(String s) {
        RaU raU = entityManager.find(RaU.class, s);
        if (raU != null){
            entityManager.remove(raU);
        }
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
    @Transactional
    public boolean saveChange(User user){
        User userToChange = entityManager.find(User.class, user.getId());
        userToChange.setFirstName(user.getFirstName());
        userToChange.setLastName(user.getLastName());
        userToChange.setEmail(user.getEmail());
        userToChange.setUsername(user.getUsername());
        userToChange.setPassword(user.getPassword());
        userToChange.setActive(user.isActive());
        RaU raU = new RaU();
        String s = user.getId().toString()+"_"+user.getRolesNew();
        RaU r = entityManager.find(RaU.class, s);
        if (r==null){
            raU.setIdRau(s);
            raU.setRole(user.getRolesNew());
            raU.setIdUser(user.getId());
            entityManager.persist(raU);
            return true;
        } else {
            return false;
        }

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
    public Set<String> getIdRoleByIdUser(Long id) {
        Query query = entityManager.createQuery("from RaU r where r.idUser =: idP");
        query.setParameter("idP", id);
        List <RaU> roles =  query.getResultList();
        Set<String> rolesSet = new HashSet<>();
        for (RaU r:roles){
            rolesSet.add(r.getRole());
        }
        return rolesSet;
    }
}
