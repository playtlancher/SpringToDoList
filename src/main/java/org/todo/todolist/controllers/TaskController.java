package org.todo.todolist.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.todo.todolist.models.Task;
import org.todo.todolist.repos.TaskRepository;
import org.todo.todolist.servises.TaskService;
import org.todo.todolist.servises.impl.TaskServiceImpl;

import java.util.Date;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String task(HttpSession session, Model model) {
        Iterable<Task> tasks = taskRepository.findAllByUserId((int) session.getAttribute("id"));
        model.addAttribute("tasks", tasks);
        System.out.println(tasks);
        return "task";
    }

    @PostMapping("/task/add")
    public String addTask(HttpSession session, @RequestParam String title, @RequestParam String description, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        TaskService taskService = new TaskServiceImpl(taskRepository);
        int userId = (int) session.getAttribute("id");
        taskService.addTask(title, description, date, userId);
        return "redirect:/task";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable(value = "id") long id, HttpSession session, Model model) {
        if (!taskRepository.existsById(id)) {
            return "redirect:/task";
        }
        int userId = (int) session.getAttribute("id");
        Iterable<Task> tasks = taskRepository.findAllByUserId(userId);
        for (Task task : tasks) {
            if (task.getId() == id) {
                model.addAttribute("task", task);
            }
        }

        return "details";
    }

    @GetMapping("/task/{id}/edit")
    public String taskEdit(@PathVariable(value = "id") long id, HttpSession session, Model model) {
        if (!taskRepository.existsById(id)) {
            return "redirect:/task";
        }
        int userId = (int) session.getAttribute("id");
        Iterable<Task> tasks = taskRepository.findAllByUserId(userId);
        for (Task task : tasks) {
            if (task.getId() == id) {
                model.addAttribute("task", task);
            }
        }
        return "task-edit";
    }

    @PostMapping("/task/{id}/edit")
    public String updateTask(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        Task task = new Task(title,description,date);
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.updateTask(id,task);
        return "redirect:/task";
    }

    @GetMapping("/task/{id}/remove")
    public String removeTask(@PathVariable(value = "id") long id, Model model) {
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.removeTask(id);
        return "redirect:/task";
    }
}
