package com.preProject.MyStackOverFlow.board.service;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.entity.BoardTag;
import com.preProject.MyStackOverFlow.board.repository.BoardRepository;
import com.preProject.MyStackOverFlow.board.repository.BoardTagRepository;
import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import com.preProject.MyStackOverFlow.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    public Board createBoard(Board board) {

        Member findMember = memberService.verifyExistsMemberId(board.getMember().getMemberId());
        board.setMember(findMember);
        board.setViewCount(0L);
        board.setAnswerCount(0);

        for (BoardTag boardTag : board.getBoardTags()) {
            boardTag.setBoard(board);
        }

        Board savedBoard = boardRepository.save(board);

        putInformationForTag(savedBoard);

        return boardRepository.save(savedBoard);
    }

    public int voteCount(long boardId) {
        Board board = findVerifiedBoard(boardId);
        int voteCount = board.getVoteCount() + 1;
        board.setVoteCount(voteCount);
        boardRepository.save(board);

        return voteCount;
    }

    public Board updateBoard(Board board) {
        Board findBoard = findVerifiedBoard(board.getBoardId());

        Optional.ofNullable(board.getTitle())
                .ifPresent(findBoard::setTitle);
        Optional.ofNullable(board.getContent())
                .ifPresent(findBoard::setContent);
        Optional.ofNullable(board.getContentTry())
                .ifPresent(findBoard::setContentTry);
        Optional.ofNullable(board.getVoteCount())
                .ifPresent(findBoard::setVoteCount);

        findBoard.setModifiedAt(new Timestamp(new Date().getTime()));

        return boardRepository.save(findBoard);
    }

    public Board findBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);

        findBoard.setViewCount(findBoard.getViewCount() + 1);

        verifyAnswerCount(findBoard);

        return boardRepository.save(findBoard);
    }

    @Transactional(readOnly = true)
    public List<Board> getAllBoardList() {
        List<Board> boardList = boardRepository.findAll();

        if (boardList.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND);
        }

        for (Board board : boardList) {
            verifyAnswerCount(board);
        }

        return boardList;
    }

    @Transactional(readOnly = true)
    public Page<Board> getAllBoardList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);

        if (!boardPage.hasContent()) {
            throw new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND);
        }

        for (Board board : boardPage.getContent()) {
            verifyAnswerCount(board);
        }

        return boardPage;
    }

    @Transactional(readOnly = true)
    public Page<Board> getAllBoardsBySearchType(String keyword, String title, String content, String memberNickname, String tagName, Pageable pageable) {

        Page<Board> response = null;

        if (title != null && !title.isEmpty()) {
            response = boardRepository.findByTitleContaining(title, pageable);
        } else if (content != null && !content.isEmpty()) {
            response = boardRepository.findByContentContaining(content, pageable);
        } else if (memberNickname != null && !memberNickname.isEmpty()) {
            response = boardRepository.findByMemberMemberNicknameContaining(memberNickname, pageable);
        } else if (tagName != null && !tagName.isEmpty()) {
            response = boardRepository.findByTagNameContaining(tagName, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            response = boardRepository.findByTitleContainingOrContentContainingOrMemberMemberNicknameContainingOrBoardTagsTagTagNameContaining(title, content, memberNickname, tagName, pageable);
        }

        if (response.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND);
        }

        List<Board> boardList = response.getContent();
        for (Board board : boardList) {
            verifyAnswerCount(board);
        }

        return response;
    }

    public void deleteBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);

        boardRepository.delete(findBoard);
    }

    public Board findVerifiedBoard(long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        return optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
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

    private void verifyAnswerCount(Board findBoard) {
        int findAnswerCount = findBoard.getAnswers().stream()
                .filter(answer -> answer.getParent() == null)
                .collect(Collectors.toList())
                .size();

        findBoard.setAnswerCount(findAnswerCount);
    }
}