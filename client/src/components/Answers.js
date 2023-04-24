import React from 'react';
import styled from 'styled-components';
import Comment from './Comment';
import AnswerLikeCount from './LikeCount';
const dummyComment = [
  {
    number: '1',
    id: 'taeyoung',
    content:
      'Comment test test test~Comment test test test~Comment test test test~Comment test test test~',
    likeCount: 5,
  },
  {
    number: '2',
    id: 'miso',
    content: 'Comment test test test~',
  },
];

const AnswerCount = styled.div`
  font-size: 25px;
  margin-top: 12px;
  margin-right: auto;
`;

const AnswerContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;
const AnswerContent = styled.div`
  font-size: 20px;
  margin-top: 30px;
  margin-left: 20px;
  white-space: pre-wrap;
`;

const AnswerId = styled.div`
  font-size: 17px;
  font-weight: 500;
`;

const Line = styled.div`
  border: 1px solid #888889;
  width: 100%;
  margin-top: 20px;
`;

const Answers = () => {
  return (
    <div>
      <AnswerCount>1 Answers</AnswerCount>
      <AnswerContainer>
        <AnswerLikeCount likeCount={dummyComment[0].likeCount} />
        <AnswerContent>{dummyComment[0].content}</AnswerContent>

        <AnswerId>{dummyComment[0].id}</AnswerId>
      </AnswerContainer>
      <Line />
      <Comment />
    </div>
  );
};

export default Answers;
