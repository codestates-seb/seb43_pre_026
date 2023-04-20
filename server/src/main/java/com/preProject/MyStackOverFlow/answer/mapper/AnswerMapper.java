package com.preProject.MyStackOverFlow.answer.mapper;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answertoToAnswer(AnswerDto.Post answerDto);
    Answer answerPutToAnswer(AnswerDto.Put answerDto);
}
