package com.preProject.MyStackOverFlow.answer.service;


import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.repository.AnswerRepository;
import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.board.service.BoardService;
import com.preProject.MyStackOverFlow.exception.BusinessLogicException;
import com.preProject.MyStackOverFlow.exception.ExceptionCode;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final MemberService memberService;
    private final BoardService boardService;
    private final AnswerRepository answerRepository;


    public Answer createAnswer(Answer answer) {
//        answer.getMember()
//        System.out.println("@@@"+answer.getMember().getMemberId());
//        Member findMember = memberService.verifyExistsMemberId(answer.getMember().getMemberId());
//        answer.setMember(findMember); // 1.멤버 외래키 등록

        Board findBoard = boardService.findVerifiedBoard(answer.getBoard().getBoardId());
        answer.setBoard(findBoard); // 2.게시글 외래키 등록
        answer.setMemberNickname(memberService.findByMemberId(answer.getMember().getMemberId()).getMemberNickname());

        answer.setLikeCount(0);
        if(answer.getParent() != null){
            findVerifiedParentAnswer(answer.getParent().getAnswerId());
            // 3. 부모 답변 있으면 등록 없으면 미등록
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
        Optional.ofNullable(answer.getLikeCount())
                .ifPresent(likeCount->answerDB.setLikeCount(likeCount));

        return answerRepository.save(answerDB);
    }

    public List<Answer> getBoardAnswers(long boardId){
        Board board = boardService.findVerifiedBoard(boardId);

        return answerRepository.findByBoard(board);
    }
    public List<Answer> getMemberAnswers(long memberId){

        Member member = memberService.findVerifiedMember(memberId);
        return answerRepository.findByMember(member);
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
