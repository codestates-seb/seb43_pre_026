package com.preProject.MyStackOverFlow.helper.swagger;

import lombok.Getter;

public class ResponseContent {

    public static final String BOARD_RESPONSE = "다음과 같은 응답이 리턴됩니다.\n" +
            "{\n" +
            "  \"boardId\": 1,\n" +
            "  \"title\": \"제목\",\n" +
            "  \"content\": \"내용\",\n" +
            "  \"contentTry\": \"시도한 내용\",\n" +
            "  \"answerCount\": 1,\n" +
            "  \"viewCount\": 7456,\n" +
            "  \"voteCount\": 102,\n" +
            "  \"createdAt\": \"2023-04-26T06:36:42.864Z\",\n" +
            "  \"modifiedAt\": \"2023-04-26T06:36:42.864Z\",\n" +
            "  \"memberNickname\": \"미숫가루설탕많이\",\n" +
            "  \"boardStatus\": \"BOARD_REGISTRATION\",\n" +
            "  \"tagNames\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"answers\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"comments\": [\n" +
            "    \"string\"\n" +
            "  ]\n" +
            "}";
    public static final String MY_PAGE_RESPONSE = "다음과 같은 응답이 리턴됩니다.\n" +
            "{\n" +
            "  \"memberName\": \"홍길동\",\n" +
            "  \"memberAnswers\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"memberBoards\": [\n" +
            "    \"string\"\n" +
            "  ]\n" +
            "  \"memberDay\": 123,\n" +
            "}";;
    public static final String ANSWER_RESPONSE = "다음과 같은 응답이 리턴됩니다.\n" +
            "{\n" +
            "  \"answerId\": 1,\n" +
            "  \"parentId\": 1,\n" +
            "  \"likeCount\": 7,\n" +
            "  \"content\": \"답변\",\n" +
            "  \"memberNickname\": \"미숫가루설탕많이\",\n" +
            "}";;
    public static final String MEMBER_RESPONSE = "다음과 같은 응답이 리턴됩니다.\n" +
            "{\n" +
            "  \"memberId\": 1,\n" +
            "  \"memberName\": \"홍길동\",\n" +
            "  \"memberNickname\": \"미숫가루설탕많이\",\n" +
            "  \"memberTitle\": \"자기소개제목?\",\n" +
            "  \"memberLink\": \"google.com\",\n" +
            "  \"memberDescription\": \"회원 소개\",\n" +
            "  \"memberPassword\": \"U@YH*t7OZkr6n\",\n" +
            "}";
}