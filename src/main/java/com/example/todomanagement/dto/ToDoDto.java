package com.example.todomanagement.dto;

import com.example.todomanagement.entity.ToDo;

public record ToDoDto(
        Long Id,
        String title,
        String description,
        boolean completed) {

    public ToDoDto(ToDo toDo) {
        this(toDo.getId(), toDo.getTitle(), toDo.getDescription(), toDo.isCompleted());
    }
}
