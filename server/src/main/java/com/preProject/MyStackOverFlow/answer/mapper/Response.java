package com.preProject.MyStackOverFlow.answer.mapper;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Response {
    public AnswerDto.Response answerToResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        Long answerId = null;
        answerId = answer.getAnswerId();

        String content = null;
        content = answer.getContent();

        boolean answerCheck = Boolean.parseBoolean(null);
        answerCheck = answer.isAnswerCheck();

        long parentId = 0L;
        if(answer.getParent() !=null){
            parentId = answer.getParent().getAnswerId();
        }

        AnswerDto.Response response = new AnswerDto.Response( answerCheck ,answerId,content, parentId);

        return response;
    }

    public List<AnswerDto.Response> answersToResponse(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.Response> list = new ArrayList<AnswerDto.Response>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToResponse( answer ) );
        }

        return list;
    }
}
