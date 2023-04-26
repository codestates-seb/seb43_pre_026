import React /*useEffect, useState,*/ from 'react';
import styled from 'styled-components';
import UserInfo from '../components/UserInfo';
import Answers from '../components/Users/Answers';
import Questions from '../components/Users/Questions';
import { useNavigate } from 'react-router-dom';
//import axios from 'axios';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;
  display: flex;
  flex-direction: column;
`;

const EditProfileButton = styled.button`
  padding: 5px 10px;
  background-color: white;
  color: gray;
  border: none;
  border-radius: 5px;
  margin-left: auto;
  border: 1px solid #ccc;
  cursor: pointer;
  &:hover {
    background-color: lightgray;
  }
`;

const Users = () => {
  const navigate = useNavigate();
  const handleEditProfile = () => {
    navigate('/useredit');
  };

  return (
    <Container>
      <>
        <UserInfo />
        <EditProfileButton onClick={handleEditProfile}>
          Edit profile
        </EditProfileButton>
      </>
      <Answers />
      <Questions />
    </Container>
  );
};

export default Users;
