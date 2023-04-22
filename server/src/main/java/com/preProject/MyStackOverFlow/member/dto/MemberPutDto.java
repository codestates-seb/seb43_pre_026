package com.preProject.MyStackOverFlow.member.dto;

import com.preProject.MyStackOverFlow.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
public class MemberPutDto {

    private long memberId;

    private String memberUserid;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String memberPassword;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String memberEmail;

    private String memberName;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String memberNickname;

    private String memberDescription;

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
