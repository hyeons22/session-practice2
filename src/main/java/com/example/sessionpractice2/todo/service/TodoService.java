package com.example.sessionpractice2.todo.service;

import com.example.sessionpractice2.todo.dto.request.TodoSaveRequestDto;
import com.example.sessionpractice2.todo.dto.request.TodoUpdateRequestDto;
import com.example.sessionpractice2.todo.dto.response.TodoResponseDto;
import com.example.sessionpractice2.todo.dto.response.TodoSaveResponseDto;
import com.example.sessionpractice2.todo.dto.response.TodoUpdateResponseDto;
import com.example.sessionpractice2.todo.entity.Todo;
import com.example.sessionpractice2.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto save(TodoSaveRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTitle(), requestDto.getContent());
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getContent()
                )).toList();
    }

    @Transactional
    public TodoResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }

    @Transactional
    public TodoUpdateResponseDto update(Long todoId, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        todo.update(requestDto.getTitle(), requestDto.getContent());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
                );
    }

    public void deleteById(Long todoId) {
        if(!todoRepository.existsById(todoId)){
            throw new IllegalArgumentException("일정이 없습니다.");
        }
        todoRepository.deleteById(todoId);
    }
}
