package com.preProject.MyStackOverFlow.answerVote.dto;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

public class AnswerVoteDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;
        @Positive
        private long answerId;
        private int answerVote;
    }
}
