import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import profile from './../assets/profile.png';
import axios from 'axios';

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
  const [userData, setUserData] = useState({
    memberNickname: '',
    profileImage: profile,
    joined: '',
  });

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get('/members/1', {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        const { memberNickname, profileImage, joined } = response.data.data;

        setUserData({
          memberNickname: memberNickname,
          profileImage: profileImage || profile,
          joined: new Date(joined).toLocaleDateString(),
        });
      } catch (error) {
        console.error('Failed to fetch user data:', error);
      }
    };

    fetchUserData();
  }, []);
  return (
    <Container>
      <ProfileContainer>
        <Image src={userData.profileImage} alt="profile" />
        <ProfileInfoWrapper>
          <Profile>
            <Name>{userData.memberNickname || 'user name'}</Name>
            <DayInfo>
              <div>Member since {userData.joined || 'today'}</div>
            </DayInfo>
          </Profile>
        </ProfileInfoWrapper>
      </ProfileContainer>
    </Container>
  );
};

export default UserInfo;
