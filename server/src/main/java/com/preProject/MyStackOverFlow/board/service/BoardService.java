package com.preProject.MyStackOverFlow.board.service;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.entity.BoardTag;
import com.preProject.MyStackOverFlow.board.repository.BoardRepository;
import com.preProject.MyStackOverFlow.board.repository.BoardTagRepository;
import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.repository.MemberRepository;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import com.preProject.MyStackOverFlow.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final MemberService memberService;
    private final BoardRepository boardRepository;
    private final BoardTagRepository boardTagRepository;
    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;

    public Board createBoard(Board board) {

        Member findMember = memberService.verifyExistsMemberId(board.getMember().getMemberId());
        board.setMember(findMember);
        board.setLikeCount(0);
        board.setViewCount(0L);

        for (BoardTag boardTag : board.getBoardTags()) {
            boardTag.setBoard(board);
        }

        Board savedBoard = boardRepository.save(board);

        putInformationForTag(savedBoard);

        return boardRepository.save(savedBoard);
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

    @Transactional(readOnly = true)
    public Page<Board> getAllBoardsBySearchType(String title, String content, String memberNickname, String tagName, Pageable pageable) {

        Page<Board> response = null;

        if (title != null && !title.isEmpty()) {
            response = boardRepository.findByTitleContaining(title, pageable);
        } else if (content != null && !content.isEmpty()) {
            response = boardRepository.findByContentContaining(content, pageable);
        } else if (memberNickname != null && !memberNickname.isEmpty()) {
            response = boardRepository.findByMemberMemberNicknameContaining(memberNickname, pageable);
        } else if (tagName != null && !tagName.isEmpty()) {
            response = boardRepository.findByTagNameContaining(tagName, pageable);
        }

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

        return optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    private void addLike(Board board) {
        memberService.verifyExistsEmail(board.getMember().getMemberEmail());

        Board findBoard = findVerifiedBoard(board.getBoardId());
        // TODO if (멤버가 해당 게시글에 좋아요를 눌렀는가?) {눌렀으면 throw exception}

        findBoard.setLikeCount(findBoard.getLikeCount() + 1);

    }

    private void putInformationForTag(Board board) {

        List<BoardTag> boardTags = board.getBoardTags();

        List<BoardTag> boardTagList = boardTags.stream()
                .map(boardTag -> {
                    Tag tag;
                    Optional<Tag> optionalTag = tagRepository.findByTagName(boardTag.getTag().getTagName());
                    if (optionalTag.isEmpty()) {
                        tag = tagRepository.save(boardTag.getTag());
                    } else {
                        tag = optionalTag.get();
                        boardTag.setTag(tag);
                    }
                    tag.addBoardTag(boardTag);
                    return boardTag;
                })
                .collect(Collectors.toList());

        boardTags.stream()
                .map(boardTagRepository::save)
                .collect(Collectors.toList());

        board.setBoardTags(boardTagList);
    }
}