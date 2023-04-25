package com.preProject.MyStackOverFlow.boardVote.controller;

import com.preProject.MyStackOverFlow.boardVote.dto.BoardVotePostDto;
import com.preProject.MyStackOverFlow.boardVote.entity.BoardVote;
import com.preProject.MyStackOverFlow.boardVote.mapper.BoardVoteMapper;
import com.preProject.MyStackOverFlow.boardVote.service.BoardVoteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/boards/vote")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class BoardVoteController {
    private final BoardVoteMapper mapper;
    private final BoardVoteService boardVoteService;

    @Operation(summary = "게시글 투표하기", description = "게시글을 추천 또는 비추천합니다. \n" +
                    "1. boardVoteCount가 1이면 추천입니다. \n" +
                    "2. boardVoteCount가 0이면 비추천입니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상 처리되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PatchMapping
    public int patchBoardVote(@Valid @RequestBody BoardVotePostDto requestBody) {

        BoardVote boardVote = mapper.boardVotePostToBoardVote(requestBody);

        return boardVoteService.voteCount(boardVote);
    }
}