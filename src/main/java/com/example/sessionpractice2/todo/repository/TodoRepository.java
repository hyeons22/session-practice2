package com.example.sessionpractice2.todo.repository;

import com.example.sessionpractice2.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
