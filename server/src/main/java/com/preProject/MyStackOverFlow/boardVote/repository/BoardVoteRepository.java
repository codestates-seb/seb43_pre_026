package com.preProject.MyStackOverFlow.boardVote.repository;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.boardVote.entity.BoardVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardVoteRepository extends JpaRepository<BoardVote, Long> {

    boolean existsByMemberId(long memberId);
    boolean existsByBoard(Board board);
    BoardVote findByMemberIdAndBoard(long memberId, Board board);
}