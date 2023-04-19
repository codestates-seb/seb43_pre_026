import React from 'react';
import styled from 'styled-components';

const dummy = {
  title: 'How do I get the current date in typst?',
  createAt: '2020-02-20',
  modifiedAt: '2020-03-22',
};

const Container = styled.div`
  padding-top: 100px;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const Title = styled.div`
  font-size: 35px;
`;
const DateContainer = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: flex-start;
`;

const CreatedAt = styled.div`
  flex: 1;
  margin-right: 15px;
`;

const ModifiedAt = styled.div`
  flex: 1;
`;

const Question = () => {
  return (
    <Container>
      <Title>{dummy.title}</Title>
      <DateContainer>
        <CreatedAt>Asked: {dummy.createAt}</CreatedAt>
        <ModifiedAt>Modified: {dummy.modifiedAt}</ModifiedAt>
      </DateContainer>
    </Container>
  );
};

export default Question;
