import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import stackoverflow from '../assets/stackoverflow.svg';
import { TbZoomQuestion } from 'react-icons/tb';
import { useNavigate } from 'react-router-dom';
import profile from '../assets/profile.png';
import axios from 'axios';

const Line = styled.div`
  border: 2px solid #f48225;
`;

const Fixed = styled.div`
  position: fixed;
  width: 100%;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.18) 0px 1px 4px;
`;

const Wrapper = styled.div`
  height: 55px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-left: 300px;
  /* margin-right: 100px; */
`;

const Logo = styled.img`
  width: 160px;
  cursor: pointer;
`;
const Loginbutton = styled.button`
  height: 38px;
  width: 68px;
  font-size: 15px;
  color: #2c5877;
  border-radius: 3px;
  margin-left: 5px;
  border: 1.2px solid #7aa7c7;
  background-color: #daecf9;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;
  cursor: pointer;

  &:hover {
    background-color: #b3d3ea;
  }
`;
const Signupbutton = styled.button`
  height: 38px;
  width: 80px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  margin-right: 180px;
  margin-left: 5px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;
  cursor: pointer;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const Search = styled.div`
  display: flex;
  align-items: center;
  position: relative;
  width: 100%;
  margin-left: 20px;
`;
const SearchIcon = styled(TbZoomQuestion)`
  position: absolute;
  color: #838c95;
  font-size: 22px;
  padding: 0 0 0 6px;
  left: 70px;
`;

const SearchInput = styled.input`
  height: 35px;
  width: 100%;
  min-width: 50px;
  border: 1.2px solid #babfc4;
  border-radius: 2px;
  padding: 0 0 0 30px;
  color: #393939;

  &:focus {
    box-shadow: 0px 0px 5px 0px #d5e5f2;
    border: 1.2px solid #006bb3;
  }
`;

const SelectBox = styled.select`
  color: #393939;
  display: flex;
  margin-right: 1px;
  align-items: center;
  position: relative;
  height: 37px;
  text-align: center;
  border: 1.2px solid #babfc4;
  border-radius: 2px;
  cursor: pointer;
`;

// const UserInfo = styled.div`
//   width: 100px;
//   border: 1px solid black;
//   margin-left: 5px;
// `;
const Logout = styled.button`
  height: 38px;
  width: 80px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  margin-right: 180px;
  margin-left: 5px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const Profile = styled.img`
  width: 35px;
  border: 1px solid gray;
  margin-left: 5px;
`;

const Header = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(
    localStorage.getItem('accessToken') ? true : false
  );
  const [selectedOption, setSelectedOption] = useState('keyword');
  const [inputValue, SetInputValue] = useState('');
  const [profileImage, setProfileImage] = useState(profile);
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
    axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
  }

  useEffect(() => {
    // const accessToken = localStorage.getItem('accessToken');
    // if (accessToken) {
    //   setIsLoggedIn(true);
    // }

    const fetchUserData = async () => {
      try {
        const response = await axios.get(
          `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`
        );
        const { profileImage } = response.data.data;

        setProfileImage(profileImage || profile);
      } catch (error) {
        console.error('Failed to fetch user data:', error);
      }
    };

    fetchUserData();
  }, []);

  const handInputValue = (e) => {
    SetInputValue(e.target.value);
  };

  const handleEnterPress = (e) => {
    if (e.keyCode === 13) {
      search();
    }
  };

  const search = () => {
    navigate(
      `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/search/list?${selectedOption}=${inputValue}`
    );
  };

  const navigate = useNavigate();

  const handleLogin = () => {
    navigate('/login');
  };

  const handleSignup = () => {
    navigate('/signup');
  };

  const handleMain = () => {
    navigate('/');
  };

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  };

  const handleUserInfo = () => {
    navigate('/users');
  };

  return (
    <Fixed>
      <Line />
      <Wrapper>
        <Logo onClick={handleMain} src={stackoverflow} />
        <Search>
          <SelectBox value={selectedOption} onChange={handleOptionChange}>
            <option value="keyword">All</option>
            <option value="title">Title</option>
            <option value="content">Content</option>
            <option value="writer">Writer</option>
            <option value="tag">Tag</option>
          </SelectBox>
          <SearchIcon />
          <SearchInput
            type="text"
            placeholder="Search..."
            value={inputValue}
            onChange={handInputValue}
            onKeyDown={handleEnterPress}
          />
        </Search>
        {isLoggedIn ? (
          <>
            <Profile onClick={handleUserInfo} src={profileImage} />
            <Logout onClick={handleLogout}>Log out</Logout>
          </>
        ) : (
          <>
            <Loginbutton onClick={handleLogin}>Log in</Loginbutton>
            <Signupbutton onClick={handleSignup}>Sign up</Signupbutton>
          </>
        )}
      </Wrapper>
    </Fixed>
  );
};

export default Header;
