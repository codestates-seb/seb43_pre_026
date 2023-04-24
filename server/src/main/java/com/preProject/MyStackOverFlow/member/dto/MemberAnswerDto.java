package com.preProject.MyStackOverFlow.member.dto;

import lombok.Builder;
import lombok.Data;

public class MemberAnswerDto {
    @Data
    @Builder
    public static class Response {
        private long answerId;
        private String  title;
    }
}
