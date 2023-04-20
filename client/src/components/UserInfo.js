import React from 'react';
import styled from 'styled-components';
import profile from '../assets/profile.png';
//import { MdCake, MdSchedule } from 'react-icons/md';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;
  display: flex;
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

const UserInfo = () => {
  return (
    <Container>
      <ProfileContainer>
        <Image src={profile} alt="profile" />
        <ProfileInfoWrapper>
          <Profile>
            <Name>user name</Name>
            <DayInfo>
              <div>Member since today</div>
              <div>Last seen this week</div>
            </DayInfo>
          </Profile>
        </ProfileInfoWrapper>
      </ProfileContainer>
    </Container>
  );
};

export default UserInfo;
