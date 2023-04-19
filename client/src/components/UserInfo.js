import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
  padding-top: 70px;
  padding-bottom: 20px;

  display: flex;
  flex-direction: row;
`;

const Image = styled.form`
  width: 115px;
  height: 115px;
  border: 1px solid gray;

  margin-right: 30px;
  margin-top: 48px;
  margin-left: 20px;
`;
const Profile = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 48px;
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

const UserInfo = () => {
  return (
    <Container>
      <Image />
      <Profile>
        <Name>김유저</Name>
        <DayInfo>
          <div>Member since today</div>
          <div>Last seen week</div>
        </DayInfo>
      </Profile>
    </Container>
  );
};

export default UserInfo;
