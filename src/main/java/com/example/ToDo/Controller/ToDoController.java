package com.example.ToDo.Controller;

import com.example.ToDo.DTO.ToDoDTO;
import com.example.ToDo.Models.ToDo;
import com.example.ToDo.Repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    ToDoRepository repository;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id){
        Optional<ToDo> Todo = repository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Todo);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ToDoDTO data){
        ToDo Todo = new ToDo();
        Todo.setDescription(data.description());
        Todo.setComplete(data.complete());
        ToDo savedTodo = repository.save(Todo);
        return ResponseEntity.status(HttpStatus.OK).body(savedTodo);
    }
    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody ToDoDTO data, @PathVariable long id){
        Optional<ToDo> Todo = repository.findById(id);
        var TodoModel = Todo.get();
        BeanUtils.copyProperties(data, TodoModel);
        repository.save(TodoModel);
        return ResponseEntity.status(HttpStatus.OK).body(Todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("successfully deleted");
    }
}
