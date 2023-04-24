package com.preProject.MyStackOverFlow.slice.repository;

import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMemberTest() {
        // given
        Member member = new Member();
        member.setMemberUserid("ui1234");
        member.setMemberPassword("ABC123!@#");
        member.setMemberEmail("1234@naver.com");
        member.setMemberName("김네임");
        member.setMemberNickname("김닉네임");
        member.setMemberDescription("안녕하세요.반갑습니다.");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertNotNull(savedMember);
        assertTrue(member.getMemberUserid().equals(savedMember.getMemberUserid()));
        assertTrue(member.getMemberPassword().equals(savedMember.getMemberPassword()));
        assertTrue(member.getMemberEmail().equals(savedMember.getMemberEmail()));
        assertTrue(member.getMemberName().equals(savedMember.getMemberName()));
        assertTrue(member.getMemberNickname().equals(savedMember.getMemberNickname()));
        assertTrue(member.getMemberDescription().equals(savedMember.getMemberDescription()));
    }

    @Test
    public void findByEmailTest() {
        // given
        Member member = new Member();
        member.setMemberUserid("ui1234");
        member.setMemberPassword("ABC123!@#");
        member.setMemberEmail("1234@naver.com");
        member.setMemberName("김네임");
        member.setMemberNickname("김닉네임");
        member.setMemberDescription("안녕하세요.반갑습니다.");

        // when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());

        // then
        assertTrue(findMember.isPresent());
        assertTrue(findMember.get().getMemberEmail().equals(member.getMemberEmail()));
    }
}
