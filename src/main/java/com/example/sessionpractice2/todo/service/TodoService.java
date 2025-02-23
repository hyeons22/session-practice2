package com.example.sessionpractice2.todo.service;

import com.example.sessionpractice2.member.entity.Member;
import com.example.sessionpractice2.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없어")
        );

        Todo todo = new Todo(requestDto.getTitle(), requestDto.getContent(), member);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContent(),
                member.getId(),
                member.getEmail()
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
    public TodoUpdateResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없어")
        );
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        // 본인 확인 조건문
        if (!todo.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("누구신데 게시물을 수정하시죠?? 본인 아니시잖아요.");
        }

        todo.update(requestDto.getTitle(), requestDto.getContent());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                member.getId(),
                member.getEmail()
        );
    }

    public void deleteById(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없어")
        );
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        // 본인 확인 조건문
        if (!todo.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("누구신데 게시물을 삭제하시죠?? 본인 아니시잖아요.");
        }

        todoRepository.deleteById(todoId);
    }
}
