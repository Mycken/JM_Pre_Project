package main.java.hiber.controller;
import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    public UserController() {
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUserProfile(Authentication authentication){
        return "redirect:/user/" + authentication.getName();
    }

    @GetMapping("/user/{username}")
    public String viewUserProfile(@PathVariable("username") String username,
                                  Model model, Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN") || authentication.getName().equals(username)){
            User user = userService.getUserByUserName(username);
            model.addAttribute("user",user);
            model.addAttribute("roles",roles);
            return "user";
        }
        return "redirect:/user/" + authentication.getName();
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "admin/allUsers";
    }
    @RequestMapping(value = "/admin/user-create", method = RequestMethod.GET)
    public String createUserForm(Model model){
        User user = new User();
        user.setPassword("100");//default password
        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("allRoles",allRoles);
        model.addAttribute("user", user);
        return "admin/user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(User user,@RequestParam("rolesNew") String rolesNew,
                             @RequestParam("password") String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userService.add(user, rolesNew);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/user-update/{id}", method = RequestMethod.GET)
    public  String updateUserForm (@PathVariable("id") Long id, Model model) {
        List<Role> allRoles = userService.getAllRoles();
        User user = userService.getUserById(id);
        model.addAttribute("userservice",userService);
        model.addAttribute("user",user);
        return "admin/user-update";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(User user,@RequestParam("rolesNew") String rolesNew,
                             @RequestParam("rolesDelete") String rolesDelete) throws Throwable {
        if (!rolesNew.equals("-")){
            user.setRolesNew(rolesNew);
        } else {
            user.setRolesNew("ROLE_USER");
        }
        if (!rolesDelete.equals("-")){
            String s = user.getId().toString() + "_" + rolesDelete;
            userService.deletePairUserRole(s);
        }
        userService.saveChange(user);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
