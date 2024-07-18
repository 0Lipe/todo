package com.example.ToDo.Repository;

import com.example.ToDo.Models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
