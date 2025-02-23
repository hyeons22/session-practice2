package com.example.sessionpractice2.common.auth.controller;

import com.example.sessionpractice2.common.auth.dto.request.AuthLoginRequestDto;
import com.example.sessionpractice2.common.auth.dto.request.AuthSignupRequestDto;
import com.example.sessionpractice2.common.auth.dto.response.AuthLoginResponseDto;
import com.example.sessionpractice2.common.auth.service.AuthService;
import com.example.sessionpractice2.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto requestDto) {
        authService.signup(requestDto);
    }

    @PostMapping("/login")
    public void login(
            @RequestBody AuthLoginRequestDto requestDto,
            HttpServletRequest request
    ) {
        AuthLoginResponseDto result = authService.login(requestDto);

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
