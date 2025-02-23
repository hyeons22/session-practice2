package com.example.sessionpractice2.common.auth.service;

import com.example.sessionpractice2.common.auth.dto.request.AuthLoginRequestDto;
import com.example.sessionpractice2.common.auth.dto.request.AuthSignupRequestDto;
import com.example.sessionpractice2.common.auth.dto.response.AuthLoginResponseDto;
import com.example.sessionpractice2.member.entity.Member;
import com.example.sessionpractice2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public void signup(AuthSignupRequestDto requestDto) {
        if(requestDto.getEmail() == null || requestDto.getPassword() == null){
            throw new IllegalArgumentException("이메일 또는 비밀번호를 입력해주세요");
        }

        Member member = new Member(requestDto.getEmail(), requestDto.getPassword());
        Member savedMember = memberRepository.save(member);
    }

    public AuthLoginResponseDto login(AuthLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일과 일치하는 회원이 없습니다.")
        );
        // 이메일 일치 확인 -> 이 부분은 앞에서 이메일과 일치하는 회원이 존재유무를 확인하기 때문에
        // 따로 아래의 조건문을 사용할 필요는 없을 것 같다.
//        if (!member.getEmail().equals(requestDto.getEmail())) {
//            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
//        }
        //비밀번호 일치 확인
        if (!member.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new AuthLoginResponseDto(
                member.getId(),
                member.getEmail()
        );
    }
}
