//package com.preProject.MyStackOverFlow.slice.service;
//
//import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
//import com.preProject.MyStackOverFlow.member.entity.Member;
//import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
//import com.preProject.MyStackOverFlow.member.service.MemberService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//
//
//@ExtendWith(MockitoExtension.class)
//public class MemberServiceTest {
//    @Mock
//    private MemberRepository memberRepository;
//
//    @InjectMocks
//    private MemberService memberService;
//
//    @Test
//    public void createMemberTest() {
//        // given
//        Member member = new Member(1);
//        given(memberRepository.findByMemberEmail(member.getMemberEmail())).willReturn(Optional.of(member));
//        // when / then
//        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
//    }
//}
