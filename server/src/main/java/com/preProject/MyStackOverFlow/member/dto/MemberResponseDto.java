package com.preProject.MyStackOverFlow.member.dto;

import com.preProject.MyStackOverFlow.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String memberUserid;
    private String memberPassword;
    private String memberEmail;
    private String memberName;
    private String memberNickname;
    private String memberDescription;
    private Member.MemberStatus memberStatus;

    public String getMemberStatus() {
        return memberStatus.getStatus();
    }
}
