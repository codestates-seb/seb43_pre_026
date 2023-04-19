import React from 'react';
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
  margin-top: -15px;
  color: #6a737c;
  font-size: 15px;
`;

const Endtext = styled.div`
  position: relative;
  top: 40px;
`;

const SignupForm = () => {
  return (
    <>
      <Form>
        <Container>
          <Label htmlFor="nicname">Display name</Label>
          <Input type="text" id="nicname" />
          <Label htmlFor="username">Username</Label>
          <Input type="text" id="username" />
          <Label htmlFor="email">Password</Label>
          <Input type="text" id="email" />
          <Label htmlFor="password">Email</Label>
          <Input type="text" id="password" />
          <Passtext>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </Passtext>
          <Button type="submit">Sign up</Button>
          <Text>
            By clicking “Sign up”, you agree to our terms of service, privacy
            policy and cookie policy
          </Text>
        </Container>
      </Form>
      <Endtext>
        Already have an account? <a href="www.naver.com">Log in</a>
      </Endtext>
    </>
  );
};

export default SignupForm;
