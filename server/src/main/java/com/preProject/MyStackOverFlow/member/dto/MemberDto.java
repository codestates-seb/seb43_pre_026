package com.preProject.MyStackOverFlow.member.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

public class MemberDto {
    @Data
    @NoArgsConstructor
    public static class Post {
        @Parameter(required = true)
        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email
        private String memberEmail;

        @Parameter(required = true)
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String memberPassword;

        @Parameter(required = true)
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String memberName;

        @Parameter(required = true)
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        @NotBlank
        private String memberNickname;
    }

    @Data
    @NoArgsConstructor
    public static class Put {

        private long memberId;

        private String memberName;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        private String memberNickname;

        private String memberTitle;

        @Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$", message = "올바른 주소값을 입력해주세요.")
        private String memberLink;

        private String memberDescription;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String memberPassword;


        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String memberPassword;
        private String memberEmail;
        private String memberName;
        private String memberTitle;
        private String memberLink;
        private String memberNickname;
        private String memberDescription;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberResponse {
        private String memberName;
        private List<MemberAnswerDto.Response> memberAnswers;
        private List<MemberBoardDto.Response> memberBoards;
        private long memberDay;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MemberPut {
        private long memberId;

        private String memberName;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        private String memberNickname;

        private String memberTitle;

        @Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$", message = "올바른 주소값을 입력해주세요.")
        private String memberLink;

        private String memberDescription;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String memberPassword;

        private long memberDay;

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }
}