import React from 'react';
import styled from 'styled-components';
import SignupOauth from '../components/Logins/LoginOauth';
import logoImg from '../assets/logo.png';
import LoginForm from '../components/Logins/LoginForm';

const Container = styled.div`
  /* position: relative; */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f1f2f3;
  flex-direction: column;
`;

const Logo = styled.img`
  width: 32px;
  height: 37px;
  margin-bottom: 16px;
`;

const LoginWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const LoginOauthWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Endtext = styled.div`
  position: relative;
  top: 40px;
`;

const Login = () => {
  return (
    <Container>
      <Logo src={logoImg} alt="stackoverflowlogo" />
      <LoginWrapper>
        <LoginOauthWrapper>
          <SignupOauth />
        </LoginOauthWrapper>
        <LoginForm />
        <Endtext>
          Dont have an account? <a href="/Signup">Sign up</a>
        </Endtext>
      </LoginWrapper>
    </Container>
  );
};

export default Login;
