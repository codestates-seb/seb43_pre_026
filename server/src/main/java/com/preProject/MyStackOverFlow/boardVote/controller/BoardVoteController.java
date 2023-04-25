package com.preProject.MyStackOverFlow.boardVote.controller;

import com.preProject.MyStackOverFlow.boardVote.dto.BoardVotePostDto;
import com.preProject.MyStackOverFlow.boardVote.entity.BoardVote;
import com.preProject.MyStackOverFlow.boardVote.mapper.BoardVoteMapper;
import com.preProject.MyStackOverFlow.boardVote.service.BoardVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/boards/vote")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class BoardVoteController {
    private final BoardVoteMapper mapper;
    private final BoardVoteService boardVoteService;

    @PatchMapping
    public int patchBoardVote(@Valid @RequestBody BoardVotePostDto requestBody) {

        BoardVote boardVote = mapper.boardVotePostToBoardVote(requestBody);

        return boardVoteService.voteCount(boardVote);
    }
}