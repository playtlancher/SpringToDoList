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
public class LoginController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
        UserService userService = new UserServiceImpl(userRepository);
        User user = new User(username, password);
        if (userService.login(user,model)){
            return "task";
        }
        return "login";
    }
}
