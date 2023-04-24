//package com.preProject.MyStackOverFlow.slice.controller;
//
//import com.google.gson.Gson;
//import com.preProject.MyStackOverFlow.helper.MemberControllerTestHelper;
//import com.preProject.MyStackOverFlow.member.controller.MemberController;
//import com.preProject.MyStackOverFlow.member.dto.MemberDto;
//import com.preProject.MyStackOverFlow.member.dto.MemberResponseDto;
//import com.preProject.MyStackOverFlow.member.entity.Member;
//import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
//import com.preProject.MyStackOverFlow.member.service.MemberService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.List;
//
//import static com.preProject.MyStackOverFlow.util.ApiDocumentUtils.getRequestPreProcessor;
//import static com.preProject.MyStackOverFlow.util.ApiDocumentUtils.getResponsePreProcessor;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(MemberController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class MemberControllerTest implements MemberControllerTestHelper {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MemberService memberService;
//
//    @MockBean
//    private MemberMapper mapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postMemberTest() throws Exception {
//        // given
//        MemberDto.Post post = new MemberDto.Post("ui1234", "ABC123!@#",
//                "1234@naver.com", "김네임", "김닉네임", "안녕하세요.");
//        String content = gson.toJson(post);
//
//        MemberDto.Response responseDto =
//                new MemberDto.Response(1L, "ui1234", "ABC123!@#", "1234@naver.com",
//                        "김네임", "김닉네임", "안녕하세요", Member.MemberStatus.MEMBER_ACTIVE);
//
//        given(mapper.memberPostDtoToMember(Mockito.any(com.preProject.MyStackOverFlow.member.dto.MemberPostDto.class))).willReturn(new Member());
//        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
//        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(responseDto);
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/members")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.memberUserid").value(post.getMemberUserid()))
//                .andExpect(jsonPath("$.data.memberPassword").value(post.getMemberPassword()))
//                .andExpect(jsonPath("$.data.memberEmail").value(post.getMemberEmail()))
//                .andExpect(jsonPath("$.data.memberName").value(post.getMemberName()))
//                .andExpect(jsonPath("$.data.memberNickname").value(post.getMemberNickname()))
//                .andExpect(jsonPath("$.data.memberDescription").value(post.getMemberDescription()))
//                .andDo(document("post-member",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberUserid").type(JsonFieldType.STRING).description("로그인 아이디"),
//                                        fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("비밀번호"),
//                                        fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("성함"),
//                                        fieldWithPath("memberNickname").type(JsonFieldType.STRING).description("별명"),
//                                        fieldWithPath("memberDescription").type(JsonFieldType.STRING).description("회원 설명")
//                                )
//                        ),
//                        responseFields(
//                               List.of(
//                                       fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                       fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                       fieldWithPath("data.memberUserid").type(JsonFieldType.STRING).description("로그인 아이디"),
//                                       fieldWithPath("data.memberPassword").type(JsonFieldType.STRING).description("비밀번호"),
//                                       fieldWithPath("data.memberEmail").type(JsonFieldType.STRING).description("이메일"),
//                                       fieldWithPath("data,memberName").type(JsonFieldType.STRING).description("성함"),
//                                       fieldWithPath("data.memberNickname").type(JsonFieldType.STRING).description("별명"),
//                                       fieldWithPath("data.memberDescription").type(JsonFieldType.STRING).description("회원 설명"),
//                                       fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("회원 상태"),
//                                       fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일자")
//                               )
//                        )
//                ));
//    }
//
//
//    @Test
//    public void putMemberTest() throws Exception {
//        // given
//       long memberId = 1L;
//       MemberDto.Put put =
//               new MemberDto.Put(memberId, "ui1234", "ABC123!@#", "1234@naver.com",
//                       "김네임", "김닉네임", "안녕하세요.");
//       String content = gson.toJson(put);
//
//       MemberDto.Response responseDto =
//               new MemberDto.Response(1L,
//                       "ui1234", "ABC123!@#", "1234@naver.com",
//                       "a김네임", "a김닉네임", "a안녕하세요.", Member.MemberStatus.MEMBER_ACTIVE);
//
//        // willReturn()이 최소한 null 은 아니어야 한다.
//        given(mapper.memberPutDtoToMember(Mockito.any(com.preProject.MyStackOverFlow.member.dto.MemberPutDto.class))).willReturn(new Member());
//        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
//        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(null);
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        put("/members/{member-id}", memberId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.memberId").value(put.getMemberId()))
//                .andExpect(jsonPath("$.data.memberUserid").value(put.getMemberUserid()))
//                .andExpect(jsonPath("$.data.memberPassword").value(put.getMemberPassword()))
//                .andExpect(jsonPath("$.data.memberEmail").value(put.getMemberEmail()))
//                .andExpect(jsonPath("$.data.memberName").value(put.getMemberName()))
//                .andExpect(jsonPath("$.data.memberNickname").value(put.getMemberNickname()))
//                .andExpect(jsonPath("$.data.memberDescription").value(put.getMemberDescription()))
//                .andDo(document("put-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                                        fieldWithPath("memberUserid").type(JsonFieldType.STRING).description("로그인 아이디").optional(),
//                                        fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("비밀번호").optional(),
//                                        fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("이메일").optional(),
//                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("성함").optional(),
//                                        fieldWithPath("memberNickname").type(JsonFieldType.STRING).description("별명").optional(),
//                                        fieldWithPath("memberDescription").type(JsonFieldType.STRING).description("회원 설명").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.memberUserid").type(JsonFieldType.STRING).description("로그인 아이디"),
//                                        fieldWithPath("data.memberPassword").type(JsonFieldType.STRING).description("비밀번호"),
//                                        fieldWithPath("data.memberEmail").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.memberName").type(JsonFieldType.STRING).description("성함"),
//                                        fieldWithPath("data.memberNickname").type(JsonFieldType.STRING).description("별명"),
//                                        fieldWithPath("data.memberDescription").type(JsonFieldType.STRING).description("회원 설명"),
//                                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("회원 상태: 활동중 / 휴면 상태 / 탈퇴 상태"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성일자")
//                                )
//                        )
//                ));
//    }
//
////    @Test
////    void getMemberTest() throws Exception {
////        // given
////
////
////        // when
////        String location = getResourceLocation();
////
////        // then
////        mockMvc.perform(getRequestBuilder(location))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.data.memberUserid").value(this.post.getMemberUserid()))
////                .andExpect(jsonPath("$.data.memberPassword").value(this.post.getMemberPassword()))
////                .andExpect(jsonPath("$.data.memberEmail").value(this.post.getMemberEmail()))
////                .andExpect(jsonPath("$.data.memberName").value(this.post.getMemberName()))
////                .andExpect(jsonPath("$.data.memberNickname").value(this.post.getMemberNickname()))
////                .andExpect(jsonPath("$.data.memberDescription").value(this.post.getMemberDescription()));
////    }
////
////    @Test
////    void deleteMemberTest() throws Exception {
////        // given
////
////
////        // when
////        String location = getResourceLocation();
////
////        // then
////        mockMvc.perform(deleteRequestBuilder(location))
////                .andExpect(status().isNoContent());
////    }
////
////    private String getResourceLocation() {
////        String location = this.postResultActions.andReturn().getResponse().getHeader("Location");
////
////        return location;
////    }
//}
