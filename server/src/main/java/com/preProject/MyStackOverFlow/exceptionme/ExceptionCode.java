package com.preProject.MyStackOverFlow.exceptionme;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "Answer not found"),
    PARENT_ANSWER_NOT_FOUND(404, "Parent Answer not found");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
