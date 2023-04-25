package com.preProject.MyStackOverFlow.controller;

import com.google.gson.Gson;
import com.preProject.MyStackOverFlow.member.controller.MemberController;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.member.service.MemberService1;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.preProject.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.preProject.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberService1 memberService1;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postMemberTest() throws Exception {

////         검색해보니 생성자를 바로 초기화 하는 거랑 set으로 초기화 하는 거랑 성능 차이가 별로 없다해서 개인적으로 선호하는 set으로 초기화 했습니다!
//        Member testMember = new Member();
//        testMember.setMemberId(1L);
//
////        MemberDto.Post post = new MemberDto.Post();
////        post.setMemberUserid("testId");
////        post.setMemberPassword("Password12*!");
////        post.setMemberEmail("test@gmail.com");
////        post.setMemberName("김테스트");
////        post.setMemberNickname("테스트닉네임");
////        post.setMemberDescription("테스트용 계정입니다.");
////
////        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class)))
////                .willReturn(testMember);
//
//        testMember.setMemberUserid("testId");
//        testMember.setMemberPassword("Password12*!");
//        testMember.setMemberEmail("test@gmail.com");
//        testMember.setMemberName("김테스트");
//        testMember.setMemberNickname("테스트닉네임");
//        testMember.setMemberDescription("테스트용 계정입니다.");
//        testMember.setMemberRole("ROLE_USER");
//
//
//        String content = gson.toJson(testMember);
////
////        given(memberService1.createMember(Mockito.any(Member.class)))
////                .willReturn(testMember);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/members")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", "/members/1"))
//                .andDo(document(
//                        "post-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberUserid").type(JsonFieldType.STRING)
//                                                .description("회원 아이디"),
//                                        fieldWithPath("memberPassword").type(JsonFieldType.STRING)
//                                                .description("회원 비밀번호 / 비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용해야 합니다."),
//                                        fieldWithPath("memberEmail").type(JsonFieldType.STRING)
//                                                .description("회원 이메일 / 이메일 형식을 지켜야 합니다."),
//                                        fieldWithPath("memberName").type(JsonFieldType.STRING)
//                                                .description("회원 성함"),
//                                        fieldWithPath("memberNickname").type(JsonFieldType.STRING)
//                                                .description("회원 별명 / 닉네임은 특수문자를 제외한 2~10자리여야 합니다."),
//                                        fieldWithPath("memberDescription").type(JsonFieldType.STRING)
//                                                .description("회원 소개").optional()
//                                )),
//                        responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("Location header / 등록된 리소스의 URI")
//                        )
//
//                ));
    }

    @Test
    public void putMemberTest() throws Exception {

//        long memberId = 1L;
//        MemberDto.Put putMember = new MemberDto.Put();
//        putMember.setMemberId(memberId);
//        putMember.setMemberUserid("testId");
//        putMember.setMemberPassword("Password12*!");
//        putMember.setMemberName("김테스트");
//        putMember.setMemberNickname("테스트닉네임");
//        putMember.setMemberDescription("테스트용 계정입니다.");
//
//        String content = gson.toJson(putMember);
//
//        MemberDto.Response response = new MemberDto.Response();
//        response.setMemberId(memberId);
//        response.setMemberUserid("testId");
//        response.setMemberPassword("Password12*!");
//        response.setMemberEmail("test@gmail.com");
//        response.setMemberName("김테스트");
//        response.setMemberNickname("테스트닉네임");
//        response.setMemberDescription("테스트용 계정입니다.");
//
//        given(mapper.memberPutDtoToMember(Mockito.any(MemberDto.Put.class)))
//                .willReturn(new Member());
//        given(memberService.updateMember(Mockito.any(Member.class)))
//                .willReturn(new Member());
//        given(mapper.memberToMemberResponse(Mockito.any(Member.class)))
//                .willReturn(response);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.put("/members/{member-id}", memberId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.memberId").value(putMember.getMemberId()))
//                .andExpect(jsonPath("$.data.memberUserid").value(putMember.getMemberUserid()))
//                .andExpect(jsonPath("$.data.memberPassword").value(putMember.getMemberPassword()))
//                .andExpect(jsonPath("$.data.memberName").value(putMember.getMemberName()))
//                .andExpect(jsonPath("$.data.memberNickname").value(putMember.getMemberNickname()))
//                .andExpect(jsonPath("$.data.memberDescription").value(putMember.getMemberDescription()))
//                .andDo(document("put-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER)
//                                                .description("회원 식별자"),
//                                        fieldWithPath("memberUserid").type(JsonFieldType.STRING)
//                                                .description("회원 아이디").optional(),
//                                        fieldWithPath("memberPassword").type(JsonFieldType.STRING)
//                                                .description("회원 비밀번호 / 비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용해야 합니다.").optional(),
//                                        fieldWithPath("memberName").type(JsonFieldType.STRING)
//                                                .description("회원 성함").optional(),
//                                        fieldWithPath("memberNickname").type(JsonFieldType.STRING)
//                                                .description("회원 별명 / 닉네임은 특수문자를 제외한 2~10자리여야 합니다.").optional(),
//                                        fieldWithPath("memberDescription").type(JsonFieldType.STRING)
//                                                .description("회원 소개").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.memberUserid").type(JsonFieldType.STRING).description("회원 아이디"),
//                                        fieldWithPath("data.memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호"),
//                                        fieldWithPath("data.memberEmail").type(JsonFieldType.STRING).description("회원 이메일"),
//                                        fieldWithPath("data.memberName").type(JsonFieldType.STRING).description("회원 성함"),
//                                        fieldWithPath("data.memberNickname").type(JsonFieldType.STRING).description("회원 별명"),
//                                        fieldWithPath("data.memberDescription").type(JsonFieldType.STRING).description("회원 소개")
//                                )
//                        ))
//                );
    }

//    @Test
//    public void getMemberTest() throws Exception {
//
//        long memberId = 1L;
//
//        MemberDto.Response response = new MemberDto.Response();
//        response.setMemberId(memberId);
//        response.setMemberUserid("testId");
//        response.setMemberPassword("Password12*!");
//        response.setMemberEmail("test@gmail.com");
//        response.setMemberName("김테스트");
//        response.setMemberNickname("테스트닉네임");
//        response.setMemberDescription("테스트용 계정입니다.");
//
//        given(memberService.findMember(Mockito.anyLong()))
//                .willReturn(new Member());
//        given(mapper.memberToMemberResponse(Mockito.any(Member.class)))
//                .willReturn(response);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders
//                                .get("/members/{member-id}", memberId)
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.memberId").value(response.getMemberId()))
//                .andExpect(jsonPath("$.data.memberUserid").value(response.getMemberUserid()))
//                .andExpect(jsonPath("$.data.memberPassword").value(response.getMemberPassword()))
//                .andExpect(jsonPath("$.data.memberEmail").value(response.getMemberEmail()))
//                .andExpect(jsonPath("$.data.memberName").value(response.getMemberName()))
//                .andExpect(jsonPath("$.data.memberNickname").value(response.getMemberNickname()))
//                .andExpect(jsonPath("$.data.memberDescription").value(response.getMemberDescription()))
//                .andDo(document(
//                        "get-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.memberUserid").type(JsonFieldType.STRING).description("회원 아이디"),
//                                        fieldWithPath("data.memberPassword").type(JsonFieldType.STRING).description("회원 비밀번호"),
//                                        fieldWithPath("data.memberEmail").type(JsonFieldType.STRING).description("회원 이메일"),
//                                        fieldWithPath("data.memberName").type(JsonFieldType.STRING).description("회원 성함"),
//                                        fieldWithPath("data.memberNickname").type(JsonFieldType.STRING).description("회원 별명"),
//                                        fieldWithPath("data.memberDescription").type(JsonFieldType.STRING).description("회원 소개")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    public void deleteMemberTest() throws Exception {
//
//        long memberId = 1L;
//
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders
//                                .delete("/members/{member-id}", memberId)
//                );
//
//        actions
//                .andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        )
//                ));
//    }
}