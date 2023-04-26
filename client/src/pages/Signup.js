import React, { useState } from 'react';
import SignupForm from '../components/SignupForm';
import SignupOauth from '../components/SignupOauth';
import styled from 'styled-components';
import SignupText from '../components/SignupText';
import axios from 'axios';

const Container = styled.div`
  height: 135vh;
  width: 100vw;
  background-color: #f1f2f3;
  display: flex;

  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

const SignupContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
`;

const FormOauth = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 30px;
`;

const Signup = () => {
  const [errorMessage, setErrorMessage] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  // useEffect(() => {
  //   const getData = async () => {
  //     try {
  //       const response = await axios.get('/members/1', {
  //         headers: {
  //           'ngrok-skip-browser-warning': '69420',
  //           // 다른 헤더가 필요하면 추가
  //         },
  //       });
  //       console.log(1, response.data);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };
  //   getData();
  // }, []);

  const handleSubmit = async (data, event) => {
    event.preventDefault();

    // 이메일 유효성 검사
    const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
    if (!emailRegex.test(data.memberEmail)) {
      setErrorMessage('이메일을 올바르게 입력해주세요');
      return;
    }

    // 패스워드 유효성 검사
    const passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if (!passwordRegex.test(data.memberPassword)) {
      setErrorMessage(
        '패스워드는 최소 8자 이상, 영문자와 숫자, 특수문자가 모두 포함되어야 합니다.'
      );
      return;
    }

    try {
      const response = await axios.post('/members/3', data);
      // 회원가입 성공 시
      if (response.status === 201) {
        setSuccessMessage('회원가입이 완료되었습니다!');
        console.log(response.data);
      }
    } catch (error) {
      // 회원가입 실패 시
      if (error.response) {
        setErrorMessage(error.response.data.message);
      }
    }
  };

  return (
    <Container>
      <SignupContainer>
        <SignupText />
        <FormOauth>
          <SignupOauth />
          <SignupForm onSubmit={handleSubmit} />
          {errorMessage && <p>{errorMessage}</p>}
          {successMessage && <p>{successMessage}</p>}
        </FormOauth>
      </SignupContainer>
    </Container>
  );
};

export default Signup;
