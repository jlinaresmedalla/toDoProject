package com.example.todomanagement.service.implementations;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.entity.ToDo;
import com.example.todomanagement.exception.ResourceNotFoundException;
import com.example.todomanagement.repository.ToDoRepository;
import com.example.todomanagement.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
