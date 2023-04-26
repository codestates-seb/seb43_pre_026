import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

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
  margin-top: 20px;
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

const SignupForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    memberUserid: '',
    memberPassword: '',
    memberEmail: '',
    memberName: '',
    memberNickname: '',
    memberDescription: '',
  });

  const handleChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  return (
    <>
      <Form onSubmit={(event) => onSubmit(formData, event)}>
        <Container>
          <Label htmlFor="memberUserid">Userid</Label>
          <Input
            type="text"
            id="memberUserid"
            name="memberUserid"
            value={formData.memberUserid}
            onChange={handleChange}
          />
          <Label htmlFor="memberPassword">Password</Label>
          <Input
            type="text"
            id="memberPassword"
            name="memberPassword"
            value={formData.memberPassword}
            onChange={handleChange}
          />
          <Label htmlFor="memberEmail">Email</Label>
          <Input
            type="text"
            id="memberEmail"
            name="memberEmail"
            value={formData.memberEmail}
            onChange={handleChange}
          />
          <Label htmlFor="memberName">Name</Label>
          <Input
            type="text"
            id="memberName"
            name="memberName"
            value={formData.memberName}
            onChange={handleChange}
          />
          <Label htmlFor="memberNickname">Nickname</Label>
          <Input
            type="text"
            id="memberNickname"
            name="memberNickname"
            value={formData.memberNickname}
            onChange={handleChange}
          />
          <Label htmlFor="memberDescription">Description</Label>
          <Input
            type="text"
            id="memberDescription"
            name="memberDescription"
            value={formData.memberDescription}
            onChange={handleChange}
          />
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
        Already have an account? <a href="/login">Log in</a>
      </Endtext>
    </>
  );
};

SignupForm.propTypes = {
  onSubmit: PropTypes.func.isRequired,
};

export default SignupForm;
