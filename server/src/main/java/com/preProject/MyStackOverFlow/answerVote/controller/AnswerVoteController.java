package com.preProject.MyStackOverFlow.answerVote.controller;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.mapper.AnswerMapper;
import com.preProject.MyStackOverFlow.answer.service.AnswerService;
import com.preProject.MyStackOverFlow.answerVote.dto.AnswerVoteDto;
import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.answerVote.mapper.AnswerVoteMapper;
import com.preProject.MyStackOverFlow.answerVote.service.AnswerVoteService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@Validated
@RequestMapping("/answer/vote")
@RestController
public class AnswerVoteController {
    private AnswerVoteMapper answerVoteMapper;
    private AnswerVoteService answerVoteService;

    public AnswerVoteController(AnswerVoteMapper answerVoteMapper, AnswerVoteService answerVoteService){
        this.answerVoteMapper = answerVoteMapper;
        this.answerVoteService = answerVoteService;
    }


    @PatchMapping("")
    public int patchAnswerlike(@Valid @RequestBody AnswerVoteDto.Post answerVotePostDto) {

        AnswerVote answerVote = answerVoteMapper.answerVotetoToAnswerVote(answerVotePostDto);
        int likeNum = answerVoteService.likeCount(answerVote);
        return likeNum;
    }
}
