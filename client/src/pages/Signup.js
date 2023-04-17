import React, {useState} from 'react';
import SignupForm from '../components/SignupForm';
import SignupOauth from '../components/SignupOauth'
import styled, { createGlobalStyle } from 'styled-components';
import SignupText from '../components/SignupText';


const GlobalStyle = createGlobalStyle`
  html, body {
    margin: 0;
    padding: 0;
  }
`;


const Signup = () => {

  const Global = styled.div`
  height: 100vh;
  width: 100vw;
  background-color: #F1F2F3;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`
const SignupContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between; 
  
`

const FormOauth = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 30px;
`


  return (
    <>
    <GlobalStyle/>
    <Global>
      <SignupContainer>
        <SignupText />
        <FormOauth>
        <SignupOauth />
        <SignupForm />
        </FormOauth>
        </SignupContainer>
    </Global>
    </>
  );
}

export default Signup;