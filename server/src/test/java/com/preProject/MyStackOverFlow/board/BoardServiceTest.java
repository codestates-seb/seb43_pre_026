package com.preProject.MyStackOverFlow.board;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.repository.BoardRepository;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @InjectMocks
    private BoardService boardService;

    @Test
    void createBoardTest() {

        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setContentTry("테스트 시도 내용");

        Mockito.when(boardRepository.save(Mockito.any(Board.class)))
                .thenReturn(board);

        Board createdBoard = boardService.createBoard(board);

        assertThat(createdBoard, is(notNullValue()));
        assertThat("테스트 제목", is(equalTo(createdBoard.getTitle())));
        assertThat("테스트 내용", is(equalTo(createdBoard.getContent())));
        assertThat("테스트 시도 내용", is(equalTo(createdBoard.getContentTry())));
    }

    @Test
    void updateBoardTest() {

        Board board1 = new Board();
        board1.setBoardId(1L);
        board1.setTitle("테스트 제목1");
        board1.setContent("테스트 내용1");
        board1.setContentTry("테스트 시도 내용1");

        Board board2 = new Board();
        board2.setBoardId(1L);
        board2.setTitle("테스트 제목2");
        board2.setContent("테스트 내용1");
        board2.setContentTry("테스트 시도 내용2");

        Mockito.when(boardRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(board1));
        Mockito.when(boardRepository.save(Mockito.any(Board.class)))
                .thenReturn(board1);

        Board updatedBoard = boardService.updateBoard(board2);

        assertThat(updatedBoard, is(notNullValue()));
        assertThat("테스트 제목2", is(equalTo(updatedBoard.getTitle())));
        assertThat("테스트 내용1", is(equalTo(updatedBoard.getContent())));
        assertThat("테스트 시도 내용2", is(equalTo(updatedBoard.getContentTry())));
    }

    @Test
    void getAllBoards() {

        Board board1 = new Board();
        board1.setTitle("제목 1");

        Board board2 = new Board();
        board2.setTitle("제목 2");

        List<Board> boards = Arrays.asList(board1, board2);

        Mockito.when(boardRepository.findAll())
                .thenReturn(boards);

        List<Board> findBoards = boardService.getAllBoards();

        assertThat(findBoards, is(notNullValue()));
        assertThat(findBoards.size(), is(equalTo(2)));
        assertThat(findBoards.get(0).getTitle(), is(equalTo("제목 1")));
        assertThat(findBoards.get(1).getTitle(), is(equalTo("제목 2")));
    }

    @Test
    void getAllBoardsBySearchType() {

    }

    @Test
    void deleteBoard() {

        long boardId = 1L;
        Board board = new Board();
        board.setBoardId(boardId);

        Mockito.when(boardRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(board));

        boardService.deleteBoard(boardId);

        Mockito.verify(boardRepository, times(1))
                .delete(Mockito.any(Board.class));
    }
}
