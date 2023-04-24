//package com.preProject.MyStackOverFlow.helper;
//
//import org.springframework.restdocs.payload.FieldDescriptor;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.restdocs.request.ParameterDescriptor;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//
//public interface MemberControllerTestHelper extends ControllerTestHelper {
//    String MEMBER_URL = "/members";
//    String RESOURCE_URI = "/{member-id}";
//
//    default String getUrl() {
//        return MEMBER_URL;
//    }
//
//    default String getURI() {
//        return MEMBER_URL + RESOURCE_URI;
//    }
//
//    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
//        return Arrays.asList(parameterWithName("member-id").description("회원 식별자 ID"));
//    }
//
//    default List<FieldDescriptor> getDefaultMemberPostRequestDescriptors() {
//
//        return List.of(
//                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                fieldWithPath("memberUserid").type(JsonFieldType.STRING).description("로그인 아이디"),
//                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("비밀번호"),
//                fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("이메일"),
//                fieldWithPath("memberName").type(JsonFieldType.STRING).description("성함"),
//                fieldWithPath("memberNickname").type(JsonFieldType.STRING).description("별명"),
//                fieldWithPath("memberDescription").type(JsonFieldType.STRING).description("회원 설명"),
//                fieldWithPath("memberState").type(JsonFieldType.STRING).description("회원 상태"),
//                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 일자")
//        );
//    }
//
//    default List<FieldDescriptor> getDefaultMemberPutRequestDescriptors() {
//
//        return List.of(
//                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                fieldWithPath("memberUserid").type(JsonFieldType.STRING).description("로그인 아이디").optional(),
//                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("비밀번호").optional(),
//                fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("이메일").optional(),
//                fieldWithPath("memberName").type(JsonFieldType.STRING).description("성함").optional(),
//                fieldWithPath("memberNickname").type(JsonFieldType.STRING).description("별명").optional(),
//                fieldWithPath("memberDescription").type(JsonFieldType.STRING).description("회원 설명").optional(),
//                fieldWithPath("memberStatus").type(JsonFieldType.STRING)
//                        .description("회원 상태: MEMBER_ACTIVE(활동중) / MEMBER_SLEEP(휴면 계정) / MEMBER_QUIT(탈퇴)")
//        );
//    }
//
//    default List<FieldDescriptor> getDefaultMemberResponseDescriptors(DataResponseType dataResponseType) {
//        String parentPath = getDataParentPath(dataResponseType);
//        return List.of(
//                fieldWithPath(parentPath.concat("memberId")).type(JsonFieldType.NUMBER).description("회원 식별자"),
//                fieldWithPath("memberUserid").type(JsonFieldType.STRING).description("로그인 아이디"),
//                fieldWithPath("memberPassword").type(JsonFieldType.STRING).description("비밀번호"),
//                fieldWithPath("memberEmail").type(JsonFieldType.STRING).description("이메일"),
//                fieldWithPath("memberName").type(JsonFieldType.STRING).description("성함"),
//                fieldWithPath("memberNickname").type(JsonFieldType.STRING).description("별명"),
//                fieldWithPath("memberDescription").type(JsonFieldType.STRING).description("회원 설명"),
//                fieldWithPath(parentPath.concat("memberStatus")).type(JsonFieldType.STRING)
//                        .description("회원 상태: MEMBER_ACTIVE(활동중) / MEMBER_SLEEP(휴면 계정) / MEMBER_QUIT(탈퇴)"),
//                fieldWithPath(parentPath.concat("createdAt")).type(JsonFieldType.STRING)
//                        .description("생성일자")
//        );
//    }
//}
