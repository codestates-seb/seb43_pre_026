package com.preProject.MyStackOverFlow.answer.entity;

import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "answer")
public class Answer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;


    @ManyToOne (fetch = FetchType.LAZY) // 게시글과 댓글 - N:1 관계 설정
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) // 멤버와 댓글 - N:1 관계 설정
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column()
    private int likeCount;

    @Column()
    private String memberNickname;

    @Column(name="answer_check", columnDefinition="BOOLEAN")
    private boolean answerCheck;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Answer parent;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<AnswerVote> answerVotes = new ArrayList<>();



    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Answer> children = new ArrayList<>();

    // 현재 댓글 id , 부모 댓글 id
    // 부모일 없을 경우 null로 데이터 넘어온다.
    // 부모가 있을 경우 부모의 id 값이 넘어온다.
}