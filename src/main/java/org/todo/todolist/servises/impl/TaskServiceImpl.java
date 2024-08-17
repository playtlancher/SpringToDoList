package org.todo.todolist.servises.impl;

import org.springframework.stereotype.Service;
import org.todo.todolist.models.Task;
import org.todo.todolist.repos.TaskRepository;
import org.todo.todolist.servises.TaskService;

import java.util.Date;
@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(String title, String description, Date date, int userId) {
        Task task = new Task(title, description, date, userId);
        taskRepository.save(task);
    }
    @Override
    public void removeTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        taskRepository.delete(task);
    }
    @Override
    public void updateTask(long taskId, Task task) {
        Task upTask = taskRepository.findById(taskId).orElseThrow();
        upTask.setTitle(task.getTitle());
        upTask.setDescription(task.getDescription());
        upTask.setDate(task.getDate());
        taskRepository.save(upTask);
    }
}
