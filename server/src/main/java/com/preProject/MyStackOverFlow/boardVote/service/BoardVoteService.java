package com.preProject.MyStackOverFlow.boardVote.service;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import com.preProject.MyStackOverFlow.boardVote.entity.BoardVote;
import com.preProject.MyStackOverFlow.boardVote.repository.BoardVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardVoteService {

    private final BoardVoteRepository boardVoteRepository;
    private final BoardService boardService;

    public int voteCount(BoardVote boardVote) {

        int vote = boardVote.getBoardVote();
        long memberId = boardVote.getMemberId();
        Board board = boardService.findVerifiedBoard(boardVote.getBoard().getBoardId());
        int voteCount = board.getVoteCount();

        if (vote == 1) {
            if (boardVoteRepository.existsByBoard(board) && boardVoteRepository.existsByMemberId(memberId)) {
                BoardVote voteDelete = boardVoteRepository.findByMemberIdAndBoard(memberId, board);

                if (voteDelete.getBoardVote() == 1) {
                    voteCount -= 1;
                } else voteCount += 1;

                boardVoteRepository.delete(voteDelete);
            } else {
                voteCount += 1;
                boardVoteRepository.save(boardVote);
            }
        } else if (vote == 0) {
            if (boardVoteRepository.existsByBoard(board) && boardVoteRepository.existsByMemberId(memberId)) {
                BoardVote voteDelete = boardVoteRepository.findByMemberIdAndBoard(memberId, board);

                if (voteDelete.getBoardVote() == 1) {
                    voteCount -= 1;
                } else voteCount += 1;

                boardVoteRepository.delete(voteDelete);
            } else {
                voteCount -= 1;
                boardVoteRepository.save(boardVote);
            }
        }

        board.setVoteCount(voteCount);
        boardService.updateBoard(board);

        return voteCount;
    }
}