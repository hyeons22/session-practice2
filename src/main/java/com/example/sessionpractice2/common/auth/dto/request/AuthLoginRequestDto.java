package com.example.sessionpractice2.common.auth.dto.request;

import lombok.Getter;

@Getter
public class AuthLoginRequestDto {

    private String email;
    private String password;
}
