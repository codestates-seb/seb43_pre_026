package com.preProject.MyStackOverFlow.answer.dto;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import lombok.AllArgsConstructor;
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
    public static class Response {

        private boolean answerCheck;

        private long answerId;

        private String content;
        private long parentId;
    }
}
