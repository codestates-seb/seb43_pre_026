package com.preProject.MyStackOverFlow.board.controller;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.mapper.BoardMapper;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
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

@Validated
@RequestMapping("/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BoardController {

    private final BoardMapper mapper;
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity postBoard(@RequestBody @Valid BoardDto.Post requestBody) {

        Board board = boardService.createBoard(mapper.boardPostToBoard(requestBody));
        URI location = UriCreator.createUri("/boards", board.getBoardId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{board-id}")
    public ResponseEntity putBoard(@PathVariable("board-id") long boardId,
                                   @RequestBody @Valid BoardDto.Put requestBody) {
        Board board = mapper.boardPutToBoard(requestBody);
        Board response = boardService.updateBoard(board);

        return new ResponseEntity<>(mapper.boardToBoardResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") long boardId) {

        Board response = boardService.findBoard(boardId);

        return new ResponseEntity<>(mapper.boardToBoardResponse(response), HttpStatus.OK);
    }

    // 게시글 조회(전체)
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


    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{board-id}")
    public int patchBoardVote(@PathVariable("board-id") long boardId) {

        return boardService.voteCount(boardId);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
