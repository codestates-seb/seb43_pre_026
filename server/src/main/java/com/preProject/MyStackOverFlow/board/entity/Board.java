package com.preProject.MyStackOverFlow.board.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String contentTry;
    private int like;
    @Column(nullable = false)
    private Timestamp createdAt = new Timestamp(new Date().getTime());
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private Timestamp modifiedAt = new Timestamp(new Date().getTime());
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus = BoardStatus.BOARD_REGISTRATION;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
//
//    @OneToMany(mappedBy = "board")
//    private List<Answer> answers = new ArrayList<>();

    public enum BoardStatus {
        BOARD_REGISTRATION("게시글 등록 상태"),
        BOARD_ANSWERED("답변 완료 상태"),
        BOARD_DELETE("게시글 삭제 상태");

        @Getter
        private String status;

        BoardStatus(String status) {
            this.status = status;
        }
    }
}
