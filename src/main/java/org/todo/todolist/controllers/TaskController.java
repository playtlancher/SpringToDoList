package org.todo.todolist.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.todo.todolist.models.Task;
import org.todo.todolist.repos.TaskRepository;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String task(Model model) {
        Iterable<Task> tasks = taskRepository.findAllByUserId((Integer) model.getAttribute("id"));
        model.addAttribute("tasks", tasks);
        return "task";
    }
}
