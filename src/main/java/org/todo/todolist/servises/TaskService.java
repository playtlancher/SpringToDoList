package org.todo.todolist.servises;

import org.todo.todolist.models.Task;

import java.util.Date;

public interface TaskService {
    void addTask(String title, String description, Date deadline, int id);
    void removeTask(long taskId);
    void updateTask(long taskId , Task task);
}
