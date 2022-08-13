package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void deleteById(long id);
    void getUserAndCarByUserID(long id);
    List<User>  getUserByCarModelAndSeries (String model, int series);
}
