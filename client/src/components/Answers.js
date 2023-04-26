import React from 'react';
import styled from 'styled-components';
import AnswerLikeCount from './AnswerLikeCount';
import AnswerComment from './AnswerComment';
import PropTypes from 'prop-types';

const Container = styled.div`
  width: 100%;
`;

const AnswerCount = styled.div`
  font-size: 25px;
  margin-top: 20px;
`;

const AnswerContainer = styled.div`
  /* display: flex;
  justify-content: space-between; */
`;

const InnerAnswer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
`;

const AnswerContent = styled.div`
  font-size: 15px;
  margin-left: 20px;

  /* white-space: pre-wrap;
  justify-content:left */
`;

const AnswerId = styled.div`
  font-size: 17px;
  font-weight: 500;
  margin-left: auto;
  padding-right: 10px;
  margin-top: auto;
`;

const Line = styled.div`
  margin-top: 5px;
  border: 1px solid #e3e6e8;
  width: 100%;
`;

const AnswerComments = styled.div`
  width: 92%;
  margin-left: 60px;
  margin-top: 20px;
`;
const CommentContainer = styled.div``;

const LineContainer = styled.div`
  width: 100%;
`;

const LineContent = styled.div`
  width: 100%;
`;
const CommentLine = styled.div`
  border: 1px solid #e3e6e8;
  width: 100%;
`;

const CommentAround = styled.div`
  display: flex;
  align-items: center;
`;

const CommentContent = styled.div`
  font-size: 15px;
  margin-top: 7px;
  margin-left: 15px;
  margin-bottom: 7px;
  color: #0d0d0d;
`;

const CommentId = styled.div`
  font-size: 17px;
  font-weight: 500;
  color: #0a95ff;
  padding-left: 3px;
`;

const Answers = ({ filteredAnswer }) => {
  console.log(filteredAnswer.answer);
  return (
    <Container>
      <AnswerCount>{filteredAnswer.answer.length} Answers</AnswerCount>
      {filteredAnswer.answer.map((answer) => (
        <AnswerContainer key={answer.answerId}>
          <LineContainer>
            <InnerAnswer>
              <AnswerLikeCount
                likeCount={answer.likeCount}
                answerId={answer.answerId}
              />
              <AnswerContent>{answer.content}</AnswerContent>
              <AnswerId>{answer.memberNickname}</AnswerId>
            </InnerAnswer>
            <AnswerComments>
              {filteredAnswer.answerComment
                .filter(
                  (answerComment) => answerComment.parentId === answer.answerId
                )
                .map((answerComment) => (
                  <CommentContainer key={answerComment.answerId}>
                    <LineContent>
                      <CommentLine />
                      <CommentAround>
                        <CommentContent>
                          {answerComment.content} -
                        </CommentContent>
                        <CommentId>{answerComment.memberNickname}</CommentId>
                      </CommentAround>
                    </LineContent>
                  </CommentContainer>
                ))}
              <AnswerComment answerComment={filteredAnswer.answerComment} />
            </AnswerComments>

            <Line />
          </LineContainer>
        </AnswerContainer>
      ))}
    </Container>
  );
};

Answers.propTypes = {
  filteredAnswer: PropTypes.object.isRequired,
  // answerId: PropTypes.number.isRequired,
};

export default Answers;
