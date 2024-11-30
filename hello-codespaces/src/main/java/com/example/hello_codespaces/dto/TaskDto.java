package com.example.hello_codespaces.dto;

import lombok.Data;
import lombok.Builder;

import java.util.Date;

@Data
@Builder
public class TaskDto {
    private long id;
    private String title;
    private boolean finished;
    private Date due;
}
