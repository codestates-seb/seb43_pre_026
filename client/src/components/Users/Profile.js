import React from 'react';
import styled from 'styled-components';
import profileimg from '../../assets/profile.png';

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

const ProfileBox = styled.div`
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

const Profile = () => {
  return (
    <ProfileContainer>
      <Image src={profileimg} alt="profile" />
      <ProfileInfoWrapper>
        <ProfileBox>
          <Name>user name</Name>
          <DayInfo>
            <div>Member since today</div>
          </DayInfo>
        </ProfileBox>
        <EditProfileButton>Edit profile</EditProfileButton>
      </ProfileInfoWrapper>
    </ProfileContainer>
  );
};

export default Profile;
