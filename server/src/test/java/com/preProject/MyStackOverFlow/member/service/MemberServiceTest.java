//package com.preProject.MyStackOverFlow.member.service;
//
//import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
//import com.preProject.MyStackOverFlow.member.entity.Member;
//import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
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
//        Member member = new Member();
//        given(memberRepository.findByMemberEmail(Mockito.anyString())).willReturn(Optional.of(member));
//        // when / then
//        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
//    }
//}
