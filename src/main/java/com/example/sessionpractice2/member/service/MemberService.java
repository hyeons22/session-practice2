package com.example.sessionpractice2.member.service;

import com.example.sessionpractice2.member.dto.request.MemberUpdateRequestDto;
import com.example.sessionpractice2.member.dto.response.MemberResponseDto;
import com.example.sessionpractice2.member.dto.request.MemberSaveRequestDto;
import com.example.sessionpractice2.member.dto.response.MemberSaveResponseDto;
import com.example.sessionpractice2.member.entity.Member;
import com.example.sessionpractice2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponseDto save(MemberSaveRequestDto requestDto) {
        Member member = new Member(requestDto.getEmail(), requestDto.getPassword());
        Member savedMember = memberRepository.save(member);

        return new MemberSaveResponseDto(savedMember.getId(), savedMember.getEmail());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getEmail()))
                .toList();
    }

    @Transactional
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없어요")
        );
        return new MemberResponseDto(member.getId(), member.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버 없어요")
        );

        member.update(requestDto.getEmail(), requestDto.getPassword());
    }

    @Transactional
    public void deleteById(Long memberId) {
        if(!memberRepository.existsById(memberId)){
            throw new IllegalArgumentException("멤버가 존재하지 않습니다.");
        }
        memberRepository.deleteById(memberId);
    }
}
