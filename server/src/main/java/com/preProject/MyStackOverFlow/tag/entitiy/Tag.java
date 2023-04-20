package com.preProject.MyStackOverFlow.tag.entitiy;

import com.preProject.MyStackOverFlow.board.entity.BoardTag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @Column(nullable = false, unique = true)
    private String tagName;
    @OneToMany(mappedBy = "tag")
    private List<BoardTag> boardTags = new ArrayList<>();

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void addBoardTag(BoardTag boardTag) {
        this.boardTags.add(boardTag);
        if (boardTag.getTag() != this) {
            boardTag.addTag(this);
        }
    }
}

