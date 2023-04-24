package com.preProject.MyStackOverFlow.member.repository;

import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);
    Member findByMemberId(Long memberId);
}
