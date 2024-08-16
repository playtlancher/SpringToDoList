package org.todo.todolist.repos;

import org.springframework.data.repository.CrudRepository;
import org.todo.todolist.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
