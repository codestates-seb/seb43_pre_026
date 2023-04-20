package com.preProject.MyStackOverFlow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),

    NOT_IMPLEMENTATION(501, "Not Implementation"),

    BOARD_NOT_FOUND(404, "Board not found"),
    TAG_NOT_FOUND(404, "Tag not found");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
