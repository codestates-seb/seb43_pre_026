import React, { /*useEffect, useState,*/ useNavigate } from 'react';
import styled from 'styled-components';
import UserInfo from '../components/UserInfo';
import Answers from '../components/Users/Answers';
import Questions from '../components/Users/Questions';
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
  // const [datas, setDatas] = useState([]);

  // useEffect(() => {
  //   const getData = async () => {
  //     try {
  //       const response = await axios.get('members/1', {
  //         headers: {
  //           'ngrok-skip-browser-warning': '69420',
  //         },
  //       });

  //       setDatas(response.data);
  //       console.log(response.data);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };
  //   getData();
  // }, []);

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
