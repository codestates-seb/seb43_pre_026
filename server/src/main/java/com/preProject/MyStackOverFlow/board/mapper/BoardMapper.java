package com.preProject.MyStackOverFlow.board.mapper;

import com.preProject.MyStackOverFlow.board.dto.BoardDto;
import com.preProject.MyStackOverFlow.board.dto.BoardTagDto;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.entity.BoardTag;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPutToBoard(BoardDto.Put put);

    default Board boardPostToBoard(BoardDto.Post post) {
        Board board = new Board();
        Member member = new Member();
        member.setMemberId(post.getMemberId());

        List<BoardTag> boardTags = post.getTagNames().stream()
                        .map(boardTagDto -> {
                            BoardTag boardTag = new BoardTag();
                            Tag tag = new Tag();
                            tag.setTagName(boardTagDto.getTagName());
                            boardTag.setTag(tag);
                            return boardTag;
                        })
                        .collect(Collectors.toList());

        board.setTitle( post.getTitle() );
        board.setContent( post.getContent() );
        board.setContentTry( post.getContentTry() );
        board.setMember(member);
        board.setBoardTags(boardTags);

        return board;
    }

    default BoardDto.Response boardToBoardResponse(Board board) {

        return BoardDto.Response.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .contentTry(board.getContentTry())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .boardStatus(board.getBoardStatus())
                .tagNames(board.getBoardTags().stream()
                        .map(boardTag -> BoardTagDto.Response.builder()
                                .tagId(boardTag.getTag().getTagId())
                                .tagName(boardTag.getTag().getTagName())
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }
}
