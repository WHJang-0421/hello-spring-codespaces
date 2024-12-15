package com.example.hello_codespaces.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@AllArgsConstructor

@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private long id;

    private String title;
    private boolean finished;
    private Date due;

    @Builder
    public Task(String title, boolean finished, Date due) {
        this.title = title;
        this.finished = finished;
        this.due = due;
    }

    public void updateTask(String title, boolean finished, Date due) {
        this.title = title;
        this.finished = finished;
        this.due = due;
    }
}
