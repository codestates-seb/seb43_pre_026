import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import axios from 'axios';

const Answer = ({ boardId }) => {
  const [answer, setAnswer] = useState('');
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
    axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
  }

  const handleAnswerSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(
        'http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/answer',
        {
          memberId: memberId,
          boardId: boardId,
          content: answer,
          answerCheck: true,
        }
      );
      setAnswer('');
      alert('등록되었습니다!');
    } catch (error) {
      console.error(error);
      alert('답변 등록에 실패하였습니다.');
    }
  };

  const handleAnswerChange = (e) => {
    setAnswer(e.target.value);
  };
  return (
    <AnswerContainer>
      <Title>Your Answer</Title>
      <AnswerForm onSubmit={handleAnswerSubmit}>
        <AnswerTextArea value={answer} onChange={handleAnswerChange} required />
        <AnswerButton type="submit">Post Your Answer</AnswerButton>
      </AnswerForm>
    </AnswerContainer>
  );
};

Answer.propTypes = {
  boardId: PropTypes.number.isRequired,
};

export default Answer;

const AnswerContainer = styled.div`
  margin-top: 30px;
  width: 100%;
  margin-bottom: 5%;
`;

const AnswerForm = styled.form``;

const Title = styled.div`
  font-size: 25px;
`;

const AnswerTextArea = styled.textarea`
  margin-top: 12px;
  width: 100%;
  height: 130px;
`;

const AnswerButton = styled.button`
  height: 40px;
  width: 135px;
  font-size: 14px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  flex-shrink: 0;
  margin-top: 5px;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;
