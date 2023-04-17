package com.preProject.MyStackOverFlow.board.mapper;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPostToBoard(BoardDto.Post post);
    Board boardPatchToBoard(BoardDto.Patch patch);
    BoardDto.Response boardToBoardResponse(Board board);
    List<BoardDto.Response> boardsToBoardResponses(List<Board> boards);
}
