package com.preProject.MyStackOverFlow.member.dto;

import lombok.Builder;
import lombok.Data;

public class MemberBoardDto {
    @Data
    @Builder
    public static class Response {
        private long boardId;
        private String  title;
    }
}
