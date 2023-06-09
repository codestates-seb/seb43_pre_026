package com.preProject.MyStackOverFlow.answerVote.repository;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    boolean existsByMemberId(long memberId);
    boolean existsByAnswer(Answer Answer);
    AnswerVote findByMemberIdAndAnswer(long memberId, Answer answer);

}
