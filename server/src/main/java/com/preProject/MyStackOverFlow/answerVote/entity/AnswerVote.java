package com.preProject.MyStackOverFlow.answerVote.entity;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "answervote")
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerVoteId;

    @ManyToOne (fetch = FetchType.LAZY) // 게시글과 댓글 - N:1 관계 설정
    @JoinColumn(name = "ANSWER_ID", nullable = false)
    private Answer answer;

    @Column()
    private long memberId;

    @Column()
    private int answerVote;



}
