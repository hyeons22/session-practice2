package com.example.sessionpractice2.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long memberId;
    private final String email;

    public TodoSaveResponseDto(Long id, String title, String content, Long memberId, String email) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.email = email;
    }
}
