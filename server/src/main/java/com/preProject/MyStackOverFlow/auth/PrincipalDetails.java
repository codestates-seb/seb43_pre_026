package com.preProject.MyStackOverFlow.auth;

import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

//시큐리티 세션 만들기 <- Authentication <- UserDetails
//1. UserDetails 만드는 작업

public class PrincipalDetails implements UserDetails {

    private Member member;
    public PrincipalDetails(Member member){
        this.member = member;
    }
    @Override //해당 유저의 권한을 리턴하는 곳
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("@@@@@@######################");
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getMemberRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
