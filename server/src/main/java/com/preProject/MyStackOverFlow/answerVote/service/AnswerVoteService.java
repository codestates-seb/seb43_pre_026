package com.preProject.MyStackOverFlow.answerVote.service;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.repository.AnswerRepository;
import com.preProject.MyStackOverFlow.answer.service.AnswerService;
import com.preProject.MyStackOverFlow.answerVote.entity.AnswerVote;
import com.preProject.MyStackOverFlow.answerVote.repository.AnswerVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {
    private final AnswerVoteRepository vRepository;

    private final AnswerService answerService;

    public int likeCount(AnswerVote answerVote){
        //UP 버튼 클릭
          //memberId , answerId 가 테이블에 있으면 like-1, delete - 한 번 눌렀던 사람
            // 전에 up 버튼을 누른 사람이라면 -1
            // 전에 down 버튼을 누른 사람이라면 +1
          //memberId , answerId 가 테이블에 없으면 like+1, create

        //DOWN 버튼 클릭
          //memberId , answerId 가 테이블에 있으면 like+1, delete - 한 번 눌렀던 사람
            // 전에 up 버튼을 누른 사람이라면 -1
            // 전에 down 버튼을 누른 사람이라면 +1
          //memberId , answerId 가 테이블에 없으면 like-1, create

        int vote = answerVote.getAnswerVote();
        long memberId = answerVote.getMemberId();
        Answer answer = answerService.findVerifiedAnswer(answerVote.getAnswer().getAnswerId());
        int like = answer.getLikeCount();

        if(vote==1){
            if(vRepository.existsByAnswer(answer) && vRepository.existsByMemberId(memberId)){
                AnswerVote voteDelte = vRepository.findByMemberIdAndAnswer(memberId, answer);
                if(voteDelte.getAnswerVote()==1) like -=1;
                else like +=1;

                vRepository.delete(voteDelte);
            }else{
                like +=1;
                vRepository.save(answerVote);
            }
        }else if(vote==0){
            if(vRepository.existsByAnswer(answer) && vRepository.existsByMemberId(memberId)){
                AnswerVote voteDelte = vRepository.findByMemberIdAndAnswer(memberId, answer);
                if(voteDelte.getAnswerVote()==1) like -=1;
                else like +=1;
                vRepository.delete(voteDelte);
            }else{
                like -=1;
                vRepository.save(answerVote);
            }

        }
        answer.setLikeCount(like);
        answerService.updateAnswer(answer);



        return like;
    }

}
