package com.preProject.MyStackOverFlow.member;


import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import com.preProject.MyStackOverFlow.member.service.MemberService1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class DBMemberService implements MemberService1 {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    MemberService1 inMemoryMemberService;


    public DBMemberService(MemberRepository memberRepository,
                             PasswordEncoder passwordEncoder,
                          InMemoryMemberService inMemoryMemberService
                            ) {
        this.inMemoryMemberService = inMemoryMemberService;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Member createMember(Member member) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Db");
        inMemoryMemberService.createMember(member);
        verifyExistsEmail(member.getMemberEmail());
        String encryptedPassword = passwordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(encryptedPassword);

//        // 추가: User Role DB에 저장
//        List<String> roles = authorityUtils.createRoles(member.getEmail());

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    public Member findMember(String email) {
        return findVerifiedMember(email);
    }

    private Member findVerifiedMember(String email) {
        Optional<Member> optionalMember =
                memberRepository.findByMemberEmail(email);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByMemberEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}