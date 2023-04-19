import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  height: auto;
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
  margin-bottom: 70px;
`;

const Title = styled.h1`
  padding-left: 20px;
  border-bottom: 1px solid gray;
  padding-bottom: 40px;
`;

const Subhead = styled.div`
  padding-top: 20px;
  padding-left: 20px;
  font-size: 25px;
`;

const EditTitleInput = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid;
  margin-top: 20px;
  margin-bottom: 30px;
  padding: 20px;

  font-size: 18px;
  font-weight: bold;
`;

const Image = styled.form`
  width: 115px;
  height: 115px;
  border: 1px solid gray;
  margin-top: 10px;
`;

const NameInput = styled.div`
  margin-top: 25px;
  > input {
    height: 30px;
    width: 950px;
    margin-top: 10px;
  }
  > textarea {
    height: 100px;
    width: 950px;
    margin-top: 10px;
  }
`;
const TitleInput = styled(NameInput)``;
const AboutMe = styled(NameInput)``;

const OtherInfo = styled.div`
  padding-left: 20px;
  > .link {
    padding-left: 20px;
  }
`;

const Input = styled.input`
  width: 950px;
  height: 30px;
  margin-top: 10px;
  margin-bottom: 30px;
`;

const Button = styled.div`
  display: flex;
  flex-direction: row;
  border: none;
  border-radius: 2px;
  margin-left: 10px;
  cursor: pointer;
`;
const Save = styled(Button)`
  height: 38px;
  width: 100px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;

  justify-content: center;
  align-items: center;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;
const Cancel = styled(Button)`
  height: 38px;
  width: 100px;
  font-size: 15px;
  color: #0a95ff;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: white;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;

  justify-content: center;
  align-items: center;

  &:hover {
    background-color: aliceblue;
    border: 1.2px solid aliceblue;
  }
`;

const UserInfoEdit = () => {
  return (
    <Container>
      <Title>Edit your profile</Title>
      <Subhead>Public information</Subhead>
      <EditTitleInput>
        <div>profile image</div>
        <Image />
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
          <textarea />
        </AboutMe>
      </EditTitleInput>
      <OtherInfo>
        <Subhead>Links</Subhead>
        <div className="link">GitHub link or username</div>
        <Input />
        <Subhead>Private information</Subhead>
        <Input />
      </OtherInfo>
      <Button>
        <Save>Save profile</Save>
        <Cancel>Cnacel</Cancel>
      </Button>
    </Container>
  );
};

export default UserInfoEdit;
