package com.preProject.MyStackOverFlow.boardVote.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class BoardVotePostDto {

    @Parameter(required = true)
    @Positive
    private long memberId;
    @Parameter(required = true)
    @Positive
    private long boardId;
    @Parameter(required = true)
    private int boardVoteCount;
}