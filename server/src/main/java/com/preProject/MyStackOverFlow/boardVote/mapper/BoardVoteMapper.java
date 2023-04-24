package com.preProject.MyStackOverFlow.boardVote.mapper;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.boardVote.dto.BoardVotePostDto;
import com.preProject.MyStackOverFlow.boardVote.entity.BoardVote;
import org.springframework.stereotype.Component;

@Component
public class BoardVoteMapper {
    public BoardVote boardVotePostToBoardVote(BoardVotePostDto post) {
        BoardVote boardVote = new BoardVote();

        Board board = new Board();
        board.setBoardId(post.getBoardId());

        boardVote.setBoard(board);
        boardVote.setBoardVote(post.getBoardVoteCount());
        boardVote.setMemberId(post.getMemberId());

        return boardVote;
    }
}