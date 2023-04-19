import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  height: auto;
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
`;

const Title = styled.h1`
  padding-bottom: 10px;
`;

const Public = styled.div`
  border-top: 1px solid gray;
  padding-top: 20px;
  font-size: 25px;
`;

const EditTitleInput = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid;

  padding: 20px;
  margin-top: 20px;
`;

const NameInput = styled.div``;
const TitleInput = styled(NameInput)``;
const AboutMe = styled(NameInput)``;

const UserInfoEdit = () => {
  return (
    <Container>
      <Title>Edit your profile</Title>
      <Public>Public information</Public>
      <EditTitleInput>
        <div>이미지</div>
        <NameInput>
          <div>name</div>
          <input />
        </NameInput>
        <TitleInput>
          <div>title</div>
          <input />
        </TitleInput>
        <AboutMe>
          <div>about me</div>
          <input />
        </AboutMe>
      </EditTitleInput>
    </Container>
  );
};

export default UserInfoEdit;
