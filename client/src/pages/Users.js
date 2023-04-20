import React from 'react';
import styled from 'styled-components';
import profile from '../assets/profile.png';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;
  display: flex;
  //flex-direction: row;
  flex-direction: column;
`;

const ProfileContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

const ProfileInfoWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
`;

const Profile = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 48px;
`;

const Image = styled.img`
  width: 115px;
  height: 115px;
  border: 1px solid gray;

  margin-right: 30px;
  margin-top: 48px;
  margin-left: 20px;
`;

const Name = styled.div`
  font-size: 45px;
  font-weight: bold;
  margin-bottom: 15px;
  margin-top: 8px;
`;

const DayInfo = styled.div`
  display: flex;
  flex-direction: row;
  color: gray;
  > div {
    margin-right: 20px;
  }
`;

const EditProfileButton = styled.button`
  padding: 5px 10px;
  background-color: white;
  color: darkgray;
  border: none;
  border-radius: 5px;
  margin-left: auto;
  border: 1px solid #ccc;
  cursor: pointer;
`;

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

const QuestionsContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const QuestionsTitle = styled.h3`
  font-size: 20px;
  margin-bottom: 10px;
  text-align: left;
  width: 100%;
`;

const QuestionsInput = styled.textarea`
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

const Users = () => {
  return (
    <Container>
      <ProfileContainer>
        <Image src={profile} alt="profile" />
        <ProfileInfoWrapper>
          <Profile>
            <Name>user name</Name>
            <DayInfo>
              <div>Member since today</div>
            </DayInfo>
            <EditProfileButton>Edit profile</EditProfileButton>
          </Profile>
        </ProfileInfoWrapper>
      </ProfileContainer>
      <AnswersContainer>
        <AnswersTitle>Answers</AnswersTitle>
        <AnswersInput placeholder="You have not answered any questions" />
      </AnswersContainer>
      <QuestionsContainer>
        <QuestionsTitle>Questions</QuestionsTitle>
        <QuestionsInput placeholder="You have not asked any questions" />
      </QuestionsContainer>
    </Container>
  );
};

export default Users;
