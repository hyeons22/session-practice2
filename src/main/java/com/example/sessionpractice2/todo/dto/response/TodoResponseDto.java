package com.example.sessionpractice2.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String title;
    private final String content;

    public TodoResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
