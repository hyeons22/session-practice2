package com.example.sessionpractice2.member.repository;

import com.example.sessionpractice2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member , Long> {
}
