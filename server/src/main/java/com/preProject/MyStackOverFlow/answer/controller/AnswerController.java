package com.preProject.MyStackOverFlow.answer.controller;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.mapper.AnswerMapper;
import com.preProject.MyStackOverFlow.answer.mapper.Response;
import com.preProject.MyStackOverFlow.answer.service.AnswerService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answer")
@Validated
@Slf4j
public class AnswerController {
    private final static String ANSWER_DEFAULT_URL = "/answer";
    private AnswerMapper answerMapper;
    private AnswerService answerService;
    private Response response;

    public AnswerController(AnswerMapper answerMapper, AnswerService answerService, Response response ) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.response = response;
    }

    @PostMapping()
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {
        Answer answer = answerService.createAnswer(answerMapper.answertoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answerId}")
    public int patchAnswerlike(@PathVariable("answerId") @Positive long answerId) {
        int likeCount = answerService.likeCount(answerId);
        return likeCount;
    }

    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("answerId") @Positive long answerId) {
        List<AnswerDto.Response> answers = response.answersToResponse(answerService.getAnswer(answerId));

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAnswers() {
        List<AnswerDto.Response> answers = response.answersToResponse(answerService.getAnswers());

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteMember(
            @PathVariable("answerId") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{answerId}")
    public ResponseEntity patchMember(
            @PathVariable("answerId") @Positive long answerId,
            @Valid @RequestBody AnswerDto.Put answerPutDto) {
        answerPutDto.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(answerMapper.answerPutToAnswer(answerPutDto));

        AnswerDto.Response responseDTO = response.answerToResponse(answerService.createAnswer(answer));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
