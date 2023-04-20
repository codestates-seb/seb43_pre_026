import React from 'react';
import styled from 'styled-components';
import Profile from '../components/Users/Profile';
import Answers from '../components/Users/Answers';
import Questions from '../components/Users/Questions';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;
  display: flex;
  //flex-direction: row;
  flex-direction: column;
`;

const Users = () => {
  return (
    <Container>
      <Profile />
      <Answers />
      <Questions />
    </Container>
  );
};

export default Users;
