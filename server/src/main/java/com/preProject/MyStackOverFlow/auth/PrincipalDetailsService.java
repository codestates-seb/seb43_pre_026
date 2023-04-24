package com.preProject.MyStackOverFlow.auth;

import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

//2. Authentication 작업
//시큐리티 설정에서 /login 요청이 오면 자동으로 loadUserByUsername함수 호출

@Component
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public PrincipalDetailsService(MemberRepository memberRepository) {
        System.out.println("123######################");
        this.memberRepository = memberRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username+"######################");
        Optional<Member> member = memberRepository.findByMemberEmail(username);
        Member findMember = member.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return new PrincipalDetails(findMember);
    }
}
