package com.preProject.MyStackOverFlow.board.controller;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.mapper.BoardMapper;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.service.MemberService;
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
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardMapper mapper;
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity postBoard(@RequestBody @Valid BoardDto.Post requestBody) {

        Board board = mapper.boardPostToBoard(requestBody);
        Board response = boardService.createBoard(board);

        return new ResponseEntity<>(mapper.boardToBoardResponse(response), HttpStatus.CREATED);
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
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
