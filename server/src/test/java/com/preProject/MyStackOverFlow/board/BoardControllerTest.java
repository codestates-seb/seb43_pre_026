package com.preProject.MyStackOverFlow.board;

import com.google.gson.Gson;
import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.mapper.BoardMapper;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private BoardService boardService;
    @Autowired
    private BoardMapper mapper;

    @Test
    void postBoardTest() throws Exception {

        BoardDto.Post post = new BoardDto.Post(1L,"테스트 제목1", "테스트 내용1", "테스트 시도한 내용1");
        Board board = mapper.boardPostToBoard(post);

        given(boardService.createBoard(Mockito.any(Board.class)))
                .willReturn(board);

        String content = gson.toJson(post);

        ResultActions actions =
                mockMvc.perform(
                        post("/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated());
    }

    @Test
    void putBoardTest() throws Exception {

        long boardId = 1L;
        BoardDto.Put put = new BoardDto.Put(1L, "테스트 수정된 제목", "테스트 수정된 내용", "테스트 수정된 시도 내용");
        Board board = mapper.boardPutToBoard(put);

        given(boardService.updateBoard(Mockito.any(Board.class)))
                .willReturn(board);

        String content = gson.toJson(put);

        ResultActions actions =
                mockMvc.perform(
                        patch("/boards/{board-id}", boardId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk());
    }

    @Test
    void getAllBoardsTest() throws Exception {

        List<Board> boards = new ArrayList<>();
        boards.add(new Board(1L, "테스트 제목1", "테스트 내용1", "테스트 시도한 내용1"));
        boards.add(new Board(2L, "테스트 제목2", "테스트 내용2", "테스트 시도한 내용2"));

        given(boardService.getAllBoards())
                .willReturn(boards);

        ResultActions actions =
                mockMvc.perform(
                        get("/boards/list")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllBoardsBySearchTypeTest() throws Exception {

        List<Board> boards = new ArrayList<>();
        boards.add(new Board(1L, "테스트 제목1", "테스트 내용1", "테스트 시도한 내용1"));
        boards.add(new Board(2L, "테스트 제목2", "테스트 내용2", "테스트 시도한 내용2"));

        Page<Board> page = new PageImpl<>(boards);

        given(boardService.getAllBoardsBySearchType(Mockito.anyString(), Mockito.anyString(), Mockito.any(Pageable.class)))
                .willReturn(page);

        ResultActions actions =
                mockMvc.perform(
                        get("/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("title", "테스트")
                                .param("content", "테스트")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "board-id, desc")
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deleteBoardTest() throws Exception {

        long boardId = 1L;

        ResultActions actions =
                mockMvc.perform(
                        delete("/boards/{board-id}", boardId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isNoContent());
    }
}

