import React, { useState } from 'react';
import styled from 'styled-components';

const AnswerContainer = styled.div`
  margin-top: 100px;
  width: 100%;
`;

const AnswerForm = styled.form``;

const Title = styled.div`
  font-size: 25px;
`;

const AnswerTextArea = styled.textarea`
  margin-top: 12px;
  width: 100%;
  height: 130px;
`;

const AnswerButton = styled.button`
  height: 40px;
  width: 135px;
  font-size: 14px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  flex-shrink: 0;
  margin-top: 5px;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const Answer = () => {
  const [answer, setAnswer] = useState('');
  const handleAnswerSubmit = (e) => {
    e.preventDefault();
    console.log(answer);
    setAnswer('');
    alert('등록되었습니다!');
  };

  const handleAnswerChange = (e) => {
    setAnswer(e.target.value);
  };
  return (
    <AnswerContainer>
      <Title>Your Answer</Title>
      <AnswerForm onSubmit={handleAnswerSubmit}>
        <AnswerTextArea value={answer} onChange={handleAnswerChange} required />
        <AnswerButton type="submit">Post Your Answer</AnswerButton>
      </AnswerForm>
    </AnswerContainer>
  );
};

export default Answer;
