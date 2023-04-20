package com.preProject.MyStackOverFlow.board.entity;

import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BoardTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardTagId;
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    public BoardTag(Board board, Tag tag) {
        this.board = board;
        this.tag = tag;
    }

    public void addTag(Tag tag) {
        this.tag = tag;
        if (!this.tag.getBoardTags().contains(this)) {
            this.tag.getBoardTags().add(this);
        }
    }

    public void addBoard(Board board) {
        this.board = board;
        if (!this.board.getBoardTags().contains(this)) {
            this.board.getBoardTags().add(this);
        }
    }
}
