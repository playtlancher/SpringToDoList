package org.todo.todolist.servises;

import org.springframework.ui.Model;
import org.todo.todolist.models.User;

public interface UserService {
    boolean login (User user, Model model);
    boolean register (User user);
}
