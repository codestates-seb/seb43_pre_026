import React from 'react';
import styled from 'styled-components';
import QuestionForm from '../components/QuestionForm';

const Container = styled.div`
  height: auto;
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
`;

//background-color: #f1f2f3;

const QuestionHead = styled.h1`
  display: flex;
  justify-content: flex-start;
  margin-left: 20px;
`;

const QuestionSubmit = () => {
  return (
    <Container>
      <QuestionHead>Ask a public question</QuestionHead>
      <QuestionForm />
    </Container>
  );
};

export default QuestionSubmit;
