package com.preProject.MyStackOverFlow.answerVote.dto;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

public class AnswerVoteDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Parameter(required = true)
        @Positive
        private long memberId;
        @Parameter(required = true)
        @Positive
        private long answerId;
        @Parameter(required = true)
        private int answerVote;
    }
}
