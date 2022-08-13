package main.java.hiber.controller;

import hiber.model.RaU;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    public UserController() {
    }

//    @Autowired
//    RaU raU;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping("/user")
    public String viewUserProfile() {return "user";}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model){
        List <RaU> rolesUsers = userService.getRolesAndIdUsers();
        for (RaU r:rolesUsers){System.out.println(r.getRole());}
        List <User> users = userService.getAllUsers();
        model.addAttribute("rolesUsers",rolesUsers);
        model.addAttribute("users",users);


        return "admin/allUsers";
    }
    @RequestMapping(value = "/admin/user-create", method = RequestMethod.GET)
    public String createUserForm(User user){
        return "admin/user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(User user){
        userService.add(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/user-update/{id}", method = RequestMethod.GET)
    public  String updateUserForm (@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "admin/user-update";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(User user){
        userService.saveChange(user);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
