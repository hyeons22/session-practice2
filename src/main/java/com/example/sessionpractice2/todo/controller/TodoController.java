package com.example.sessionpractice2.todo.controller;

import com.example.sessionpractice2.common.consts.Const;
import com.example.sessionpractice2.todo.dto.request.TodoSaveRequestDto;
import com.example.sessionpractice2.todo.dto.request.TodoUpdateRequestDto;
import com.example.sessionpractice2.todo.dto.response.TodoResponseDto;
import com.example.sessionpractice2.todo.dto.response.TodoSaveResponseDto;
import com.example.sessionpractice2.todo.dto.response.TodoUpdateResponseDto;
import com.example.sessionpractice2.todo.service.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto requestDto){
        return ResponseEntity.ok(todoService.save(memberId, requestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll(){
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId){
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto requestDto
    ){
        return ResponseEntity.ok(todoService.update(memberId, todoId, requestDto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteById(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId){
        todoService.deleteById(memberId, todoId);
    }
}
