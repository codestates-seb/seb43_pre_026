package com.preProject.MyStackOverFlow.answer.mapper;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answertoToAnswer(AnswerDto.Post answerDto) {
        if (answerDto == null) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(answerDto.getMemberId());

        Board board = new Board();
        board.setBoardId(answerDto.getBoardId());

        Answer answer = new Answer();
        answer.setMember(member);
        answer.setBoard(board);
        answer.setContent(answerDto.getContent());
        answer.setParent(answerDto.getParent());

        return answer;
    }
    Answer answerPutToAnswer(AnswerDto.Put answerDto);
}