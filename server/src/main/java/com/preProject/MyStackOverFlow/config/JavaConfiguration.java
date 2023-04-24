package com.preProject.MyStackOverFlow.config;

import com.preProject.MyStackOverFlow.member.DBMemberService;
import com.preProject.MyStackOverFlow.member.InMemoryMemberService;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import com.preProject.MyStackOverFlow.member.service.MemberService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class JavaConfiguration {


    // Role까지 DB에서 관리
    @Primary
    @Bean
    public MemberService1 dbMemberServiceV2(MemberRepository memberRepository,
                                            PasswordEncoder passwordEncoder,
                                            InMemoryMemberService inMemoryMemberService) {
        return new DBMemberService(memberRepository, passwordEncoder, inMemoryMemberService );
    }
}