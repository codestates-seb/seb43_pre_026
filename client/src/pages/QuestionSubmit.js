import React from 'react';
import styled from 'styled-components';
import QuestionForm from '../components/QuestionForm';

const Container = styled.div`
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
  padding-top: 70px;
  padding-bottom: 100px;
`;

//background-color: #f1f2f3;

const QuestionHead = styled.h1`
  display: flex;
  justify-content: flex-start;
  padding-bottom: 20px;
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
