package com.preProject.MyStackOverFlow.board.service;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(Board board) {
        // TODO verifyExistsMember : 해당 멤버가 있는지 조회하는 로직 추가
        board.setLikeCount(0);
        board.setViewCount(0L);

        return boardRepository.save(board);
    }

    public Board updateBoard(Board board) {
        Board findBoard = findVerifiedBoard(board.getBoardId());

        Optional.ofNullable(board.getTitle())
                .ifPresent(findBoard::setTitle);
        Optional.ofNullable(board.getContent())
                .ifPresent(findBoard::setContent);
        Optional.ofNullable(board.getContentTry())
                .ifPresent(findBoard::setContentTry);

        findBoard.setModifiedAt(new Timestamp(new Date().getTime()));

        return boardRepository.save(findBoard);
    }

    public Board findBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);

        findBoard.setViewCount(findBoard.getViewCount() + 1);

        return boardRepository.save(findBoard);
    }

    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // TODO tag, username 검색 기능도 추가해야함
    @Transactional(readOnly = true)
    public Page<Board> getAllBoardsBySearchType(String title, String content, Pageable pageable) {

        Page<Board> response = null;

        if (title != null && !title.isEmpty()) {
            response = boardRepository.findByTitleContaining(title, pageable);
        } else if (content != null && !content.isEmpty()) {
            response = boardRepository.findByContentContaining(content, pageable);
        }
//        else {
//            throw new BusinessLogicException
//        }

        return response;
    }

    public void deleteBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);

        boardRepository.delete(findBoard);

// TODO   if (findBoard.getBoardStatus() == Board.BoardStatus.BOARD_DELETE) {
//            throw new BusinessLogicException(ExceptionCode.BOARD_ALREADY_DELETED);
//        }
//        findBoard.setBoardStatus(Board.BoardStatus.BOARD_DELETE);
//       게시글이 삭제되었다는 문구를 출력해주는 페이지?? 필요?
//        findBoard.setTitle("삭제된 게시글입니다.");
//        findBoard.setContent("삭제된 게시글입니다.");
//        findBoard.setContentTry("삭제된 게시글입니다.");
    }

    private Board findVerifiedBoard(long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        return optionalBoard.get();
        // TODO BusinessLogicException 추가 후 optionalBoard.orElseThrow()로 변경
    }

    private void addLike(Board board) {
        // TODO verifyExistsMember : 해당 멤버가 있는지 조회하는 로직 추가
        Board findBoard = findVerifiedBoard(board.getBoardId());
        // TODO if (멤버가 해당 게시글에 좋아요를 눌렀는가?) {눌렀으면 throw exception}

        findBoard.setLikeCount(findBoard.getLikeCount() + 1);

    }
}
