package com.example.todomanagement.controller;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.service.implementations.ToDoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@AllArgsConstructor
public class ToDoController {

    private ToDoServiceImpl toDoService;

    //Build an ToDo REST API

    @PostMapping("/add")
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDo = toDoService.addToDO(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }



}
