package org.todo.todolist.repos;

import org.springframework.data.repository.CrudRepository;
import org.todo.todolist.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByUserId(int id);
}
