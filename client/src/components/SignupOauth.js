import React from 'react';
import styled from 'styled-components';
import google from '../assets/google.png'


const Button = styled.button`
  height: 40px;
  width: 363px;
  background-color: rgb(255, 255, 255);
  border: 1.2px solid #D6D9DC;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;

  &:hover{
    background-color: #F8F9F9;
    cursor: pointer;

  }
`

const Log = styled.img`
  height: 18px;
  margin: 4px; 
`

const SignupOauth = () => {
  return (
    <div>
      <Button> 
      <Log src={google} alt="google logo"></Log>
        Sign up with Google</Button>
    </div>
  );
}

export default SignupOauth;