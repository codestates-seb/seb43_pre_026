package com.preProject.MyStackOverFlow.answer.service;


import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.repository.AnswerRepository;
import com.preProject.MyStackOverFlow.exceptionme.BusinessLogicException;
import com.preProject.MyStackOverFlow.exceptionme.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        answer.setLikeCount(1);
        if(answer.getParent() != null){
            findVerifiedParentAnswer(answer.getParent().getAnswerId());
        }
        return answerRepository.save(answer);
    }

    public int likeCount(long answerId){
        Answer answer = findVerifiedAnswer(answerId);
        int likeCount = answer.getLikeCount()+1;
        answer.setLikeCount(likeCount);
        answerRepository.save(answer);
        return likeCount;
    }

    public Answer updateAnswer(Answer answer) {
        Answer answerDB = findVerifiedAnswer(answer.getAnswerId());
        Optional.ofNullable(answer.getContent())
                .ifPresent(title->answerDB.setContent(title));

        return answerRepository.save(answerDB);
    }

    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    public void deleteAnswer(long answerId){
        Answer answer = findVerifiedAnswer(answerId);
        answerRepository.delete(answer);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

    @Transactional(readOnly = true)
    public void findVerifiedParentAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);

                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.PARENT_ANSWER_NOT_FOUND));
    }


}
