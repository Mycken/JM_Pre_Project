package hiber;
import hiber.model.User;
import hiber.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import hiber.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        List<User> users;

        //userService.add(new User("User1","SurUser1","user1@mail.ru"));
        //userService.deleteById(6);
        users = userService.getAllUsers();
        for (User user : users) {
            //System.out.println("Id = "+user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }

        context.close();
    }
}
