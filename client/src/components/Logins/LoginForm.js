import React from 'react';
import styled from 'styled-components';

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
  width: 100%;
  cursor: pointer;
  &:hover {
    background-color: #2c70b8;
  }
`;

const LoginFormComponent = () => {
  return (
    <LoginForm>
      <EmailFormInputWrapper>
        <FormLabel>Email</FormLabel>
        <FormInput />
      </EmailFormInputWrapper>
      <PasswordFormInputWrapper>
        <FormLabel>Password</FormLabel>
        <FormInput />
      </PasswordFormInputWrapper>
      <FormButton>Log In</FormButton>
    </LoginForm>
  );
};

export default LoginFormComponent;
