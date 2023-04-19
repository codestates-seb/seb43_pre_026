package com.preProject.MyStackOverFlow.board.dto;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.entity.Tag;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class BoardDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Positive
        private long memberId;
        @NotEmpty(message = "제목을 작성해 주세요.")
        @Length(max = 100, message = "제목은 100자 이상 작성할 수 없습니다.")
        private String title;
        @NotEmpty(message = "발생한 문제를 작성해 주세요.")
        private String content;
        @NotEmpty(message = "문제를 해결하기 위해 시도한 내용을 작성해 주세요.")
        private String contentTry;
        private List<String> tagNames;
    }

    @Data
    @AllArgsConstructor
    public static class Patch {

        @Positive
        private long boardId;
        @Length(max = 100, message = "제목은 100자 이상 작성할 수 없습니다.")
        private String title;
        private String content;
        private String contentTry;
    }

    @Data
    @Builder
    public static class Response {
        private long boardId;
        private String title;
        private String content;
        private String contentTry;
        private int likeCount;
        private long viewCount;
        private Board.BoardStatus boardStatus;
    }
}