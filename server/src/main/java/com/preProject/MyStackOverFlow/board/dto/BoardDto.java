package com.preProject.MyStackOverFlow.board.dto;

import com.preProject.MyStackOverFlow.board.entity.Board;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class BoardDto {

    @Getter
    public static class Post {

        @Positive
        private long memberId;
        @NotBlank(message = "제목을 작성해 주세요.")
        @Length(max = 100, message = "제목은 100자 이상 작성할 수 없습니다.")
        private String title;
        @NotBlank(message = "발생한 문제를 작성해 주세요.")
        private String content;
        @NotBlank(message = "문제를 해결하기 위해 시도한 내용을 작성해 주세요.")
        private String contentTry;
    }

    @Data
    public static class Patch {

        @Positive
        private long boardId;
        @NotBlank(message = "제목을 작성해 주세요.")
        @Length(max = 100, message = "제목은 100자 이상 작성할 수 없습니다.")
        private String title;
        @NotBlank(message = "발생한 문제를 작성해 주세요.")
        private String content;
        @NotBlank(message = "문제를 해결하기 위해 시도한 내용을 작성해 주세요.")
        private String contentTry;
    }

    @Getter
    public static class Response {
        @Positive
        private long boardId;
        @NotBlank(message = "제목을 작성해 주세요.")
        @Length(max = 100, message = "제목은 100자 이상 작성할 수 없습니다.")
        private String title;
        @NotBlank(message = "발생한 문제를 작성해 주세요.")
        private String content;
        @NotBlank(message = "문제를 해결하기 위해 시도한 내용을 작성해 주세요.")
        private String contentTry;
        private Board.BoardStatus boardStatus;
    }
}