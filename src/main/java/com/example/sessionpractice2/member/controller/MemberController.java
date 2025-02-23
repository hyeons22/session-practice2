package com.example.sessionpractice2.member.controller;

import com.example.sessionpractice2.member.dto.request.MemberUpdateRequestDto;
import com.example.sessionpractice2.member.dto.response.MemberResponseDto;
import com.example.sessionpractice2.member.dto.request.MemberSaveRequestDto;
import com.example.sessionpractice2.member.dto.response.MemberSaveResponseDto;
import com.example.sessionpractice2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> save(@RequestBody MemberSaveRequestDto requestDto){
        return ResponseEntity.ok(memberService.save(requestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId){
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PutMapping("/members/{memberId}")
    public void update(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto
    ){
        memberService.update(memberId, requestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteById(@PathVariable Long memberId){
        memberService.deleteById(memberId);
    }
}
