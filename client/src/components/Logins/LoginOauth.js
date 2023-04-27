import React from 'react';
import styled from 'styled-components';
import google from '../../assets/google.png';

const Button = styled.button`
  height: 40px;
  width: 330px;
  background-color: rgb(255, 255, 255);
  border: 1.2px solid #d6d9dc;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;

  &:hover {
    background-color: #f8f9f9;
    cursor: pointer;
  }
`;

const Logo = styled.img`
  height: 18px;
  margin: 4px;
`;

const SignupOauth = () => {
  return (
    <div>
      <Button>
        <Logo src={google} alt="google logo"></Logo>
        Log in with Google
      </Button>
    </div>
  );
};

export default SignupOauth;
