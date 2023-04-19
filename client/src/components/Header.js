import React, { useState } from 'react';
import styled from 'styled-components';
import stackoverflow from '../assets/stackoverflow.svg';
import { TbZoomQuestion } from 'react-icons/tb';

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
  left: 58px;
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
`;

const UserInfo = styled.div`
  width: 100px;
  border: 1px solid black;
  margin-left: 5px;
`;
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

const Header = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [selectedOption, setSelectedOption] = useState('All');

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <Fixed>
      <Line />
      <Wrapper>
        <Logo src={stackoverflow} />
        <Search>
          <SelectBox value={selectedOption} onChange={handleOptionChange}>
            <option value="All">All</option>
            <option value="Title">Title</option>
            <option value="Writer">Writer</option>
            <option value="Tag">Tag</option>
          </SelectBox>
          <SearchIcon />
          <SearchInput type="text" placeholder="Search..." />
        </Search>
        {isLoggedIn ? (
          <>
            <UserInfo />
            <Logout handleLogout={handleLogout}>Log out</Logout>
          </>
        ) : (
          <>
            <Loginbutton>Log in</Loginbutton>
            <Signupbutton>Sign up</Signupbutton>
          </>
        )}
      </Wrapper>
    </Fixed>
  );
};

export default Header;
