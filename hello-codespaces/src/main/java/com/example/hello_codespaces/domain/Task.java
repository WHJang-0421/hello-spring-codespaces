package com.example.hello_codespaces.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Builder;

@Getter
public class Task {
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

    public void initId(long id) {
        this.id = id;
    }

    public void updateTask(String title, boolean finished, Date due) {
        this.title = title;
        this.finished = finished;
        this.due = due;
    }
}
