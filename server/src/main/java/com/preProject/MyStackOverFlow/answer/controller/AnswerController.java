package com.preProject.MyStackOverFlow.answer.controller;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.mapper.AnswerMapper;
import com.preProject.MyStackOverFlow.answer.mapper.Response;
import com.preProject.MyStackOverFlow.answer.service.AnswerService;
import com.preProject.MyStackOverFlow.helper.swagger.ResponseContent;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "AnswerController", description = "답변/댓글 API")
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

    @Operation(summary = "답변/댓글 작성", description = "답변/댓글을 작성합니다. \n" +
                    "1. answerCheck가 true이면 답변입니다. \n" +
                    "2. answerCheck가 true이고 parent에 answerId를 작성하시면 answerId의 답변에 대한 댓글입니다. \n" +
                    "3. answerCheck가 false이면 게시글(질문)에 달린 댓글입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "답변/댓글이 등록되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @PostMapping()
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {
        Answer answer = answerService.createAnswer(answerMapper.answertoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "답변 투표 수 수정", description = "답변 식별자(answerId)에 해당하는 투표 수를 수정합니다.")
    @Parameter(name = "answer-id", description = "답변 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "투표 수가 수정되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @PatchMapping("/{answerId}")
    public int patchAnswerlike(@PathVariable("answerId") @Positive long answerId) {
        int likeCount = answerService.likeCount(answerId);
        return likeCount;
    }

//    @GetMapping("/board/{boardId}")
//    public ResponseEntity getBoardAnswers(@PathVariable("boardId") @Positive long boardId) {
//        List<AnswerDto.Response> answers = response.answersToResponse(answerService.getBoardAnswers(boardId));
//
//        return new ResponseEntity<>(answers, HttpStatus.OK);
//    }
//
//    @GetMapping("/member/{memberId}")
//    public ResponseEntity getMemberAnswers(@PathVariable("memberId") @Positive long memberId) {
//        List<AnswerDto.Response> answers = response.answersToResponse(answerService.getMemberAnswers(memberId));
//
//        return new ResponseEntity<>(answers, HttpStatus.OK);
//    }


    @Operation(summary = "답변 조회", description = "답변을 조회합니다. \n" +
              ResponseContent.ANSWER_RESPONSE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAnswers() {
        List<AnswerDto.Response> answers = response.answersToResponse(answerService.getAnswers());

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @Operation(summary = "답변/댓글 정보 삭제", description = "답변 식별자(answerId)에 해당하는 답변/댓글을 삭제합니다.")
    @Parameter(name = "board-id", description = "게시글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "게시글이 삭제되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteMember(
            @PathVariable("answerId") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "답변/댓글 수정", description = "답변/댓글 식별자(answerId)에 해당하는 답변/댓글을 수정합니다. \n" +
            "수정이 필요한 정보만 입력하시면 됩니다. \n" + ResponseContent.ANSWER_RESPONSE)
    @Parameter(name = "answer-id", description = "답변/댓글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
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
