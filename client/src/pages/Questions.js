import React from 'react';
import Question from '../components/Question';
import styled from 'styled-components';
import Answer from '../components/Answer';
import QuestionHeader from '../components/QuestionHeader';

const Container = styled.div`
  padding-top: 100px;
  width: 800px;
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 0 auto;
`;

const Questions = () => {
  return (
    <Container>
      <QuestionHeader />
      <Question />
      <Answer />
    </Container>
  );
};

export default Questions;
