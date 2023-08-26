package com.example.todomanagement.controller;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.service.implementations.ToDoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@AllArgsConstructor
public class ToDoController {

    private ToDoServiceImpl toDoService;

    //Build an ToDo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDo = toDoService.addToDO(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> returnToDoDto(@PathVariable Long id) {
        ToDoDto toDoDto = toDoService.returnToDoDto(id);
        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public ResponseEntity<List<ToDoDto>> returnAllToDoDto() {
        return new ResponseEntity<>(toDoService.returnAllToDoDto(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoDto> updateToDoDto(@PathVariable Long id, @RequestBody ToDoDto toDoDto) {
        ToDoDto updatedToDo = toDoService.updateToDoDto(id, toDoDto);
        return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDoDto(@PathVariable Long id) {
        toDoService.deleteToDoDto(id);
        return new ResponseEntity<>("ToDo deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/updateStatus/{id}/{completed}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @PathVariable boolean completed) {
        toDoService.updateStatus(id, completed);
        return new ResponseEntity<>("ToDo status updated successfully", HttpStatus.OK);
    }

}
