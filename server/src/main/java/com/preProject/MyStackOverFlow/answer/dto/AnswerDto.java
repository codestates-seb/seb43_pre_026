package com.preProject.MyStackOverFlow.answer.dto;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String content;
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

        private long answerId;

        private String content;
        private long parentId;
    }
}
