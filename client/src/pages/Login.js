import React, { useState } from 'react';
import styled from 'styled-components';
import SignupOauth from '../components/Logins/LoginOauth';
import logoImg from '../assets/logo.png';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';

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

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
  text-align: center;
  width: 280px;
`;

const EmailFormInputWrapper = styled.div`
  width: 100%;
  margin-bottom: 16px;
  text-align: left;
`;

const PasswordFormInputWrapper = styled.div`
  width: 100%;
  margin-bottom: 16px;
  text-align: left;
`;

const FormLabel = styled.label`
  display: block;
  margin-bottom: 4px;
  text-align: left;
  font-weight: 600;
`;

const FormInput = styled.input`
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
  margin-bottom: 16px;
  width: 100%;
  box-sizing: border-box;
`;

const FormButton = styled.button`
  background-color: #2f80ed;
  color: white;
  padding: 8px 16px;
  border-radius: 3px;
  border: none;
  width: 280px;
  cursor: pointer;
  &:hover {
    background-color: #2c70b8;
  }
`;

const Login = () => {
  const navigate = useNavigate();
  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: '',
  });

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    const newFormdata = {
      ...loginFormData,
      [id]: value,
    };
    setLoginFormData(newFormdata);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        'http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/process_login',
        {
          username: loginFormData.username,
          password: loginFormData.password,
        }
      );
      // const memberId = response.memberId;
      const accessToken = response.headers.authorization.split(' ')[1];
      const refreshToken = response.headers.refresh;
      const memberId = response.headers.memberid;

      localStorage.setItem(
        'accessToken',
        JSON.stringify({ token: accessToken, memberId: memberId })
      );
      localStorage.setItem('refreshToken', refreshToken);
      setLoginFormData({
        username: '',
        password: '',
      });
      navigate('/');
    } catch (error) {
      console.error(error);
    }
  };

  // /v11/auth/login

  // {nickname: '영태', username: '영태', email: 'taeyoung1012@naver.com', password: '!dudxo1012'}

  return (
    <>
      <Header />
      <Container>
        <Logo src={logoImg} alt="stackoverflowlogo" />
        <LoginWrapper>
          <LoginOauthWrapper>
            <SignupOauth />
          </LoginOauthWrapper>
          <LoginForm onSubmit={handleSubmit}>
            <EmailFormInputWrapper>
              <FormLabel>UserId</FormLabel>
              <FormInput
                type="text"
                id="username"
                onChange={handleInputChange}
                value={loginFormData.userId}
              />
            </EmailFormInputWrapper>
            <PasswordFormInputWrapper>
              <FormLabel>Password</FormLabel>
              <FormInput
                type="password"
                id="password"
                onChange={handleInputChange}
                value={loginFormData.password}
              />
            </PasswordFormInputWrapper>
            <FormButton>Log In</FormButton>
          </LoginForm>
          <p>Dont have an account? Sign up</p>
        </LoginWrapper>
      </Container>
    </>
  );
};

export default Login;
