package task2_3_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import task2_3_1.model.User;
import task2_3_1.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        User user = new User(name, lastname, email);
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser() {
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String lastname,
                           @RequestParam String email) {
        User user = userService.getById(id);
        user.setName(name);
        user.setLastname(lastname);
        user.setEmail(email);
        userService.edit(user);
        return "redirect:/";
    }
}
