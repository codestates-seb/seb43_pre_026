package com.preProject.MyStackOverFlow.boardVote.entity;

import com.preProject.MyStackOverFlow.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BoardVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardVoteId;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private long memberId;
    private int boardVote;
}