package com.example.todomanagement.service;

import com.example.todomanagement.dto.ToDoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ToDoService {

    ToDoDto addToDO(ToDoDto toDoDto);
    ToDoDto returnToDoDto(Long id);
    List<ToDoDto> returnAllToDoDto();
    ToDoDto updateToDoDto(Long id, ToDoDto toDoDto);
    void deleteToDoDto(Long id);
}
