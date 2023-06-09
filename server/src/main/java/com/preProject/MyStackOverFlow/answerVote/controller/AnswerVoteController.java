package com.preProject.MyStackOverFlow.answerVote.controller;

import com.preProject.MyStackOverFlow.answerVote.dto.AnswerVoteDto;
import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.answerVote.mapper.AnswerVoteMapper;
import com.preProject.MyStackOverFlow.answerVote.service.AnswerVoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/answer/vote")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "AnswerVoteController", description = "답변 투표 API")
@RestController
public class AnswerVoteController {
    private AnswerVoteMapper answerVoteMapper;
    private AnswerVoteService answerVoteService;

    public AnswerVoteController(AnswerVoteMapper answerVoteMapper, AnswerVoteService answerVoteService){
        this.answerVoteMapper = answerVoteMapper;
        this.answerVoteService = answerVoteService;
    }

    @Operation(summary = "답변 투표하기", description = "답변을 추천 또는 비추천합니다. \n" +
            "1. answerVote가 1이면 추천입니다. \n" +
            "2. answerVote가 0이면 비추천입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @PatchMapping
    public int patchAnswerlike(@Valid @RequestBody AnswerVoteDto.Post answerVotePostDto) {

        AnswerVote answerVote = answerVoteMapper.answerVotetoToAnswerVote(answerVotePostDto);
        int likeNum = answerVoteService.likeCount(answerVote);
        return likeNum;
    }
}
