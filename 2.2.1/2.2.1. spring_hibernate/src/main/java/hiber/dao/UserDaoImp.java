package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void deleteById(long id) {
      User user = sessionFactory.getCurrentSession().load(User.class, id);
      if (user != null) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Override
   public void getUserAndCarByUserID(long id) {
      User user = sessionFactory.getCurrentSession().load(User.class, id);
      System.out.println(user);
      Car car = sessionFactory.getCurrentSession().load(Car.class, id);
      System.out.println(car);

   }

   @Override
   public List<User>  getUserByCarModelAndSeries(String model, int series) {
      TypedQuery <User> query = sessionFactory.getCurrentSession().createQuery("SELECT c.user from Car c join c.user WHERE c.model =: carMod AND c.series =: carSer")
              .setParameter("carMod", model ).setParameter("carSer", series);
      List <User> resultList = query.getResultList();
      return resultList;

//      String HQL = "FROM Cat AS ca LEFT JOIN FETCH ca.user WHERE ca.model = :carMo";
//      Car car = sessionFactory.getCurrentSession().createQuery(HQL, Car.class)
//              .setParameter("carMo", model).uniqueResult();
//      System.out.println(car);
//      System.out.println(car.getUser());

   }


}
