package com.preProject.MyStackOverFlow.board.controller;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.mapper.BoardMapper;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// TODO SwaggerTest

@Validated
@RequestMapping("/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoardController {

    private final BoardMapper mapper;
    private final BoardService boardService;

    @Operation(summary = "게시글 등록", description = "게시글 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "게시글이 정상적으로 등록되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PostMapping
    public ResponseEntity postBoard(@RequestBody @Valid BoardDto.Post requestBody) {

        Board board = boardService.createBoard(mapper.boardPostToBoard(requestBody));
        URI location = UriCreator.createUri("/boards", board.getBoardId());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "게시글 정보 수정", description = "게시글 식별자(boardId)에 해당하는 게시글을 수정합니다. \n" +
            "수정이 필요한 정보만 입력하시면 됩니다.")
    @Parameter(name = "board-id", description = "게시글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "게시글이 수정되었습니다.", response = BoardDto.Response.class),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PutMapping("/{board-id}")
    public ResponseEntity putBoard(@PathVariable("board-id") long boardId,
                                   @RequestBody @Valid BoardDto.Put requestBody) {
        Board board = mapper.boardPutToBoard(requestBody);
        Board response = boardService.updateBoard(board);

        return new ResponseEntity<>(mapper.boardToBoardResponse(response), HttpStatus.OK);
    }

    @Operation(summary = "게시글 정보 조회", description = "게시글 식별자(boardId)에 해당하는 게시글을 조회합니다.")
    @Parameter(name = "board-id", description = "게시글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "게시글이 조회되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") long boardId) {

        Board response = boardService.findBoard(boardId);

        return new ResponseEntity<>(mapper.boardToBoardResponse(response), HttpStatus.OK);
    }

    @Operation(summary = "게시글 전체 조회", description = "전체 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "전체 게시글이 조회되었습니다."),
            @ApiResponse(code = 404, message = "게시글을 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping
    public ResponseEntity getAllBoards(@PageableDefault(sort = "board-id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Board> boards = boardService.getAllBoards();

        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BoardDto.Response> response = boards.stream()
                .map(mapper::boardToBoardResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "게시글 분류별 조회", description = "분류에 해당하는 게시글을 조회합니다.")
    @Parameter(name = "title / content / memberNickname / tagName", description = "제목 / 내용 / 회원 닉네임 / 태그"
            , example = "유어클래스 / 프로젝트가 잘 안돼요 / 미숫가루설탕많이 / javascript")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "게시글이 조회되었습니다."),
            @ApiResponse(code = 404, message = "게시글을 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping("/list")
    public ResponseEntity getAllBoardsBySearchType(@RequestParam(required = false) String title,
                                                   @RequestParam(required = false) String content,
                                                   @RequestParam(required = false) String memberNickname,
                                                   @RequestParam(required = false) String tagName,
                                                   @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> boards = boardService.getAllBoardsBySearchType(title, content, memberNickname, tagName, pageable);


        List<BoardDto.Response> response = boards.getContent().stream()
                .map(mapper::boardToBoardResponse)
                .filter(distinctByKey(BoardDto.Response::getBoardId)) // 중복된 boardId의 값을 가진 response 필터링
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "게시글 정보 삭제", description = "게시글 식별자(boardId)에 해당하는 게시글을 삭제합니다.")
    @Parameter(name = "board-id", description = "게시글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "게시글이 삭제되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "게시글 투표 수 수정", description = "게시글 식별자(boardId)에 해당하는 투표 수를 수정합니다.")
    @Parameter(name = "board-id", description = "게시글 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "투표 수가 수정되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PatchMapping("/{board-id}")
    public int patchBoardVote(@PathVariable("board-id") long boardId) {

        return boardService.voteCount(boardId);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
