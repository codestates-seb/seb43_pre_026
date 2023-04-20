import React from 'react';
import styled from 'styled-components';

const AnswersContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const AnswersTitle = styled.h3`
  font-size: 20px;
  margin-bottom: 10px;
  text-align: left;
  width: 100%;
`;

const AnswersInput = styled.textarea`
  width: 100%;
  height: 100px;
  padding: 0 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  resize: none;
  margin-bottom: 10px;
  text-align: center;

  ::placeholder {
    font-size: 15px;
    text-align: center;
    margin: auto;
    padding: 40px;
  }
`;

const Answers = () => {
  return (
    <AnswersContainer>
      <AnswersTitle>Answers</AnswersTitle>
      <AnswersInput placeholder="You have not answered any questions" />
    </AnswersContainer>
  );
};

export default Answers;
