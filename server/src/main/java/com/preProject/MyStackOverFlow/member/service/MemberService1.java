package com.preProject.MyStackOverFlow.member.service;

import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface MemberService1 {
    Member createMember(Member member);
}
