import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const AnswersContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const AnswersTitle = styled.h3`
  font-size: 20px;
  margin-bottom: 10px;
  text-align: left;
  width: 100%;
`;

const AnswersInput = styled.div`
  width: 100%;
  height: 100px;
  padding: 0 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  resize: none;
  margin-bottom: 10px;
  text-align: center;

  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 15px;
  color: gray;
  margin: auto;
  padding: 20px;
`;

const Answers = () => {
  const [data, setData] = useState([]);
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
    axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
  }

  useEffect(() => {
    const getAnswerData = async () => {
      try {
        const response = await axios.get(`/members/${memberId}`);

        setData(response.data.data.memberAnswers);
      } catch (error) {
        console.error(error);
      }
    };
    getAnswerData();
  }, []);

  return (
    <AnswersContainer>
      <AnswersTitle>Answers</AnswersTitle>
      {data ? (
        data.map((answer) => (
          <AnswersInput key={answer.boardId}>{answer.title}</AnswersInput>
        ))
      ) : (
        <AnswersInput>You have not answered any questions</AnswersInput>
      )}
    </AnswersContainer>
  );
};

export default Answers;
