package com.example.todomanagement.service.implementations;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.entity.ToDo;
import com.example.todomanagement.exception.ResourceNotFoundException;
import com.example.todomanagement.repository.ToDoRepository;
import com.example.todomanagement.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    @Override
    public ToDoDto addToDO(ToDoDto toDoDto) {
        //Convert ToDoDto to ToDo jpa entity
        ToDo toDo = new ToDo(toDoDto);
        //Save ToDo jpa entity to database
        ToDo saved = toDoRepository.save(toDo);
        //Convert ToDo jpa entity to ToDoDto
        return new ToDoDto(saved);
    }

    @Override
    public ToDoDto returnToDoDto(Long id) {
        //Find ToDo jpa entity by id
        ToDo toDo = toDoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id " + id));
        //Convert ToDo jpa entity to ToDoDto
        return new ToDoDto(toDo);
    }

    @Override
    public List<ToDoDto> returnAllToDoDto() {
        //Find all ToDo jpa entities
        List<ToDo> allTodDos = toDoRepository.findAll();
        //Convert all ToDo jpa entities to ToDoDto
        return allTodDos.stream().map(ToDoDto::new).collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateToDoDto(Long id, ToDoDto toDoDto) {
        //Find ToDo jpa entity by id
        ToDo toDo = toDoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id " + id));
        // Update ToDo jpa entity
        toDo.setTitle(toDoDto.title());
        toDo.setDescription(toDoDto.description());
        toDo.setCompleted(toDoDto.completed());
        //Save ToDo jpa entity to database
        ToDo updated = toDoRepository.save(toDo);
        //Convert ToDo jpa entity to ToDoDto
        return new ToDoDto(updated);
    }

    @Override
    public void deleteToDoDto(Long id) {
        //Find ToDo jpa entity by id
        ToDo toDo = toDoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id " + id));
        //Delete ToDo jpa entity from database
        toDoRepository.delete(toDo);
    }

    @Override
    public void updateStatus(Long id, boolean completed) {
        //Find ToDo jpa entity by id
        ToDo toDo = toDoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id " + id));
        //Update ToDo jpa entity
        toDo.setCompleted(completed);
        //Save ToDo jpa entity to database
        toDoRepository.save(toDo);
    }

}
