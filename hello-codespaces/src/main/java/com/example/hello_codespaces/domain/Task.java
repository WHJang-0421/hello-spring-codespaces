package com.example.hello_codespaces.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    private String id;
    private String title;
    private boolean finished;
    private Date due;

    public void updateTask(String title, boolean finished, Date due) {
        this.title = title;
        this.finished = finished;
        this.due = due;
    }
}
