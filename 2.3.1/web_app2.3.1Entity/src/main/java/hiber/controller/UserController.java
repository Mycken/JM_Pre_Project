package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    public long i;
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    public UserController() {
    }
    @GetMapping("/users")
    public String listAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "allUsers";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    public  String updateUserForm (@PathVariable("id") Long id, Model model) {
        i = id;
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        // почему-то значение id не передаются из формы user-update
        // Выходит null. Пришлось сделать такой костыль
        user.setId(i);
        userService.saveChange(user);
        return "redirect:/users";
    }
}
