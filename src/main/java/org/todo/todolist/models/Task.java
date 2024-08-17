package org.todo.todolist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String status = "Scheduled";
    private Date date;
    private int userId;

    public Task(String title, String description, Date date, int userId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }
    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Task() {
    }
}
