package com.preProject.MyStackOverFlow.answerVote.mapper;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answerVote.dto.AnswerVoteDto;
import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class AnswerVoteMapper {
    public AnswerVote answerVotetoToAnswerVote(AnswerVoteDto.Post answerVoteDto){
        AnswerVote answerVote = new AnswerVote();

        Answer answer = new Answer();
        answer.setAnswerId(answerVoteDto.getAnswerId());

        answerVote.setAnswer(answer);
        answerVote.setAnswerVote(answerVoteDto.getAnswerVote());
        answerVote.setMemberId(answerVoteDto.getMemberId());

        return answerVote;
    }
}
