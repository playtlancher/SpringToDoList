package org.todo.todolist.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.todo.todolist.models.User;
import org.todo.todolist.repos.UserRepository;
import org.todo.todolist.servises.UserService;
import org.todo.todolist.servises.impl.UserServiceImpl;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        UserService userService = new UserServiceImpl(userRepository);
        User user = new User(username, password, email);
        if (userService.register(user)) {
            return "redirect:/login";
        }
        model.addAttribute("error", "Username or password is incorrect");
        return "registration";
    }
}
