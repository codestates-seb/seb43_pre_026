package com.preProject.MyStackOverFlow.helper;

import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.preProject.MyStackOverFlow.member.entity.Member.MemberStatus.MEMBER_ACTIVE;

public class StubData {
    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new MemberDto.Post("ui1234", "ABC123!@#",
                "1234@naver.com", "김네임", "김닉네임", "안녕하세요.반갑습니다."));
        stubRequestBody.put(HttpMethod.PUT, new MemberDto.Put(0, "ui1234", "ABC123!@#",
                "1234@gmail.com", "김네임", "김닉네임", "안녕하세요.반갑습니다.", MEMBER_ACTIVE));
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Member getSingleResponseBody(long memberId) {
            Member member = new Member("aui1234", "$ABC123!@#", "a1234@gmail.com",
                    "a김네임", "a김닉네임", "a안녕하세요.반갑습니다.");
            member.setMemberId(memberId);
            member.setMemberStatus(MEMBER_ACTIVE);
            return member;
        }

        public static Member getSingleResponseBody(long memberId, Map<String, String> updatedInfo) {
            String memberUserid = Optional.ofNullable(updatedInfo.get("memberUserid")).orElse("ui1234");
            String memberPassword = Optional.ofNullable(updatedInfo.get("memberPassword")).orElse("ABC123!@#");
            String memberEmail = Optional.ofNullable(updatedInfo.get("memberEmail")).orElse("1234@naver.com");
            String memberName = Optional.ofNullable(updatedInfo.get("memberName")).orElse("김네임");
            String memberNickname = Optional.ofNullable(updatedInfo.get("memberNickname")).orElse("김닉네임");
            String memberDescription = Optional.ofNullable(updatedInfo.get("memberDescription")).orElse("안녕하세요.반갑습니다.");
            Member member = new Member(memberUserid, memberPassword, memberEmail, memberName, memberNickname, memberDescription);
            member.setMemberId(memberId);
            member.setMemberStatus(MEMBER_ACTIVE);
            return member;
        }
    }
}
