import React from 'react';
import styled from 'styled-components';
import QuestionForm from '../components/QuestionSubmit/QuestionForm';

const Global = styled.div`
  background-color: #f1f2f3;
`;

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 100px;
`;

const QuestionHead = styled.h1`
  display: flex;
  justify-content: flex-start;
  padding-bottom: 20px;
`;

const QuestionSubmit = () => {
  return (
    <Global>
      <Container>
        <QuestionHead>Ask a public question</QuestionHead>
        <QuestionForm />
      </Container>
    </Global>
  );
};

export default QuestionSubmit;
