import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const QuestionsContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const QuestionsTitle = styled.h3`
  font-size: 20px;
  margin-bottom: 10px;
  text-align: left;
  width: 100%;
`;

const QuestionsInput = styled.div`
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

const Questions = () => {
  const [data, setData] = useState([]);
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
  }
  useEffect(() => {
    const getQuestionData = async () => {
      try {
        const response = await axios.get(`/members/${memberId}`, {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });

        setData(response.data.data.memberBoards);
        console.log('콘솔 로그', data);
      } catch (error) {
        console.error(error);
      }
    };
    getQuestionData();
  }, []);

  return (
    <QuestionsContainer>
      <QuestionsTitle>Questions</QuestionsTitle>
      {data ? (
        data.map((question) => (
          <QuestionsInput key={question.boardId}>
            {question.title}
          </QuestionsInput>
        ))
      ) : (
        <QuestionsInput>You have not asked any questions</QuestionsInput>
      )}
    </QuestionsContainer>
  );
};

export default Questions;
