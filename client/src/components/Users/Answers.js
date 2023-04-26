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

  useEffect(() => {
    const getAnswerData = async () => {
      try {
        const response = await axios.get('/members/1', {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });

        setData(response.data.memberAnswers);
        //console.log(response.data.memberAnswers);
      } catch (error) {
        console.error(error);
      }
    };
    getAnswerData();
  }, []);

  //"memberAnswers":
  // const dumyList = [
  //   {
  //     boardId: 1,
  //     title: '6번 do I get the current date in typst?',
  //   },
  //   {
  //     boardId: 2,
  //     title: '대체 어떻게 하나요?',
  //   },
  //   {
  //     boardId: 3,
  //     title: '답이 안나와요.',
  //   },
  // ];

  return (
    <AnswersContainer>
      <AnswersTitle>Answers</AnswersTitle>
      {data.length > 0 ? (
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
