package org.todo.todolist.servises;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.todo.todolist.models.User;

public interface UserService {
    boolean login(User user, HttpSession session);

    boolean register(User user);
}
