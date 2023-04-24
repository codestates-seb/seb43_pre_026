package com.preProject.MyStackOverFlow.answer.dto;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;
        @Positive
        private long boardId;
        private String content;
        private boolean answerCheck;
        private Answer parent;
        public Post(String content){
            this.content = content;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Put {
        private long answerId;
        private String content;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Response {

        private long answerId;
        private long parentId;
        private int likeCount;
        private String content;
        private String memberNickname;
    }
}
