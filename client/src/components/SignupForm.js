import axios from 'axios';
import React, { useState } from 'react';
import styled from 'styled-components';

const Label = styled.label`
  width: 300px;
  text-align: left;
  font-size: 18px;
  font-weight: 700;
  margin-bottom: 3px;
`;

const Input = styled.input`
  height: 30px;
  width: 300px;
  font-size: 17px;
  border-radius: 3px;
  border: 1.5px solid #babfc4;
  margin-bottom: 20px;
`;

const Form = styled.form`
  flex-direction: column;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  background-color: white;
  height: 550px;
  width: 360px;
  border-radius: 7px;
  box-shadow: rgba(0, 0, 0, 0.15) 0px 10px 24px;
`;

const Button = styled.button`
  width: 300px;
  height: 40px;
  font-size: 15px;
  color: white;
  background-color: #0a95ff;
  border: 1.2px solid #0a95ff;
  cursor: pointer;
  border-radius: 4px;
  margin-top: 55px;
  margin-bottom: 15px;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 30px;
  margin-bottom: 20px;
`;

const Text = styled.div`
  width: 300px;
  color: #6a737c;
  font-size: 15px;
`;

const Passtext = styled.div`
  width: 300px;
  color: #6a737c;
  font-size: 15px;
`;

const Endtext = styled.div`
  position: relative;
  top: 40px;
`;

const PassworConfirm = styled.div`
  font-size: 14px;
  margin-top: -16px;
  margin-bottom: 5px;
  color: red;
`;

const SignupForm = () => {
  const [isValidPassword, setIsValidPassword] = useState(false);
  const [isValidEmail, setIsValidEmail] = useState(false);
  const [formData, setFormData] = useState({
    nickname: '',
    username: '',
    email: '',
    password: '',
  });

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    const newFormData = {
      ...formData,
      [id]: value,
    };
    setFormData(newFormData);
    passwordConfirm(newFormData);
    emailConfirm(newFormData);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(
        '/members',
        {
          memberUserid: formData.nickname,
          memberNickname: formData.nickname,
          memberName: formData.username,
          memberEmail: formData.email,
          memberPassword: formData.password,
          memberDescription: formData.nickname,
        },
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(() => {
        alert('회원가입에 성공하였습니다!');
        setFormData({
          nickname: '',
          username: '',
          email: '',
          password: '',
        });
        Array.from(document.querySelectorAll('input')).forEach(
          (input) => (input.value = '')
        );
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const emailRegEx =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i;
  const passwordRegEx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;

  const emailConfirm = (newFormData) => {
    if (emailRegEx.test(newFormData.email)) {
      setIsValidEmail(true);
    } else {
      setIsValidEmail(false);
    }
  };

  const passwordConfirm = (newFormData) => {
    if (passwordRegEx.test(newFormData.password)) {
      setIsValidPassword(true);
    } else {
      setIsValidPassword(false);
    }
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Container>
          <Label htmlFor="nickname">Display name</Label>
          <Input
            type="text"
            id="nickname"
            onChange={handleInputChange}
            value={formData.nickname}
          />
          <Label htmlFor="username">Username</Label>
          <Input
            type="text"
            id="username"
            onChange={handleInputChange}
            value={formData.username}
          />
          <Label htmlFor="email">Email</Label>
          <Input
            type="text"
            id="email"
            onChange={handleInputChange}
            value={formData.email}
          />
          <PassworConfirm>
            {!formData.email || isValidEmail
              ? null
              : '이메일 형식으로 입력해주세요.'}
          </PassworConfirm>
          <Label htmlFor="password">Password</Label>
          <Input
            type="password"
            id="password"
            onChange={handleInputChange}
            value={formData.password}
          />
          <PassworConfirm>
            {!formData.password || isValidPassword
              ? null
              : '특수문자, 영문, 숫자 포함 8글자 이상 입력해주세요.'}
          </PassworConfirm>
          <Passtext>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </Passtext>
          <Button type="submit" disabled={!isValidPassword || !isValidEmail}>
            Sign up
          </Button>
          <Text>
            By clicking “Sign up”, you agree to our terms of service, privacy
            policy and cookie policy
          </Text>
        </Container>
      </Form>
      <Endtext>
        Already have an account? <a href="/login">Log in</a>
      </Endtext>
    </>
  );
};

export default SignupForm;
