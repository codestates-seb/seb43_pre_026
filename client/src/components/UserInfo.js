import React from 'react';
import styled from 'styled-components';
import profile from '../assets/profile.png';
//import { MdCake, MdSchedule } from 'react-icons/md';

/*const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;

  display: flex;
  flex-direction: row;
`;

const Image = styled.img`
  width: 100px;
  height: 100px;
  border-radius: 5%;

  margin-right: 30px;
  margin-top: 48px;
  margin-left: 15px;
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
  //margin-top: 3px;
`;
const DayInfo = styled.div`
  display: flex;
  flex-direction: row;
  color: gray;
  > div {
    margin-right: 20px;
  }
  > div svg {
    margin-right: 5px;
  }
`;*/

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 20px;
  display: flex;
  //flex-direction: row;
  flex-direction: column;
  //맞춤(flex-direction 다름)
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
  //맞춤
`;

const Image = styled.img`
  width: 115px;
  height: 115px;
  border: 1px solid gray;

  margin-right: 30px;
  margin-top: 48px;
  margin-left: 20px;
  //styled.form -> style.img 수정해주세용
`;

const Name = styled.div`
  font-size: 45px;
  font-weight: bold;
  margin-bottom: 15px;
  margin-top: 8px;
  //맞춤
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
      {/*<Image src={profile} alt="profile" />
      <Profile>
        <Name>김유저</Name>
        <DayInfo>
          <div>
            <MdCake />
            Member since today
          </div>
          <div>
            <MdSchedule />
            Last seen week
          </div>
        </DayInfo>
  </Profile>*/}
    </Container>
  );
};

export default UserInfo;
