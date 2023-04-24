package com.preProject.MyStackOverFlow.boardVote.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class BoardVotePostDto {

    @Positive
    private long memberId;
    @Positive
    private long boardId;
    private int boardVoteCount;
}