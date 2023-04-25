package com.preProject.MyStackOverFlow.member.service;

import com.preProject.MyStackOverFlow.auth.utils.CustomAuthorityUtils;
import com.preProject.MyStackOverFlow.config.SecurityConfiguration;
import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.helper.event.MemberRegistrationApplicationEvent;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;


    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher,
                         PasswordEncoder passwordEncoder,
                         CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getMemberEmail());

        // 추가: Password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(encryptedPassword);

        // 추가: DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getMemberEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(savedMember));
        return savedMember;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getMemberUserid())
                .ifPresent(memberUserid -> findMember.setMemberUserid(memberUserid));
        Optional.ofNullable(member.getMemberEmail())
                .ifPresent(memberEmail -> findMember.setMemberEmail(memberEmail));
        Optional.ofNullable(member.getMemberName())
                .ifPresent(memberName -> findMember.setMemberName(memberName));
        Optional.ofNullable(member.getMemberNickname())
                .ifPresent(memberNickname-> findMember.setMemberNickname(memberNickname));
        Optional.ofNullable(member.getMemberDescription())
                .ifPresent(memberDescription -> findMember.setMemberDescription(memberDescription));

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    public Member findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByMemberEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
    public Member verifyExistsMemberId(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty())
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);

        return member.get();
    }
}
