package task2_3_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task2_3_1.model.User;
import task2_3_1.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{username}")
    public String userMain(@PathVariable String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/admin")
    public String adminMain() {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String showUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser() {
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email
    ) {
        User user = new User(username, password, email);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser() {
        return "delete";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser() {
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam Long id,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {
        User user = userService.getById(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userService.edit(user);
        return "redirect:/admin";
    }
}
