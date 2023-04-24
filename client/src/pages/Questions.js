import React, { useState, useEffect } from 'react';
import Question from '../components/Question';
import styled from 'styled-components';
import AnswerForm from '../components/AnswerForm';
import QuestionHeader from '../components/QuestionHeader';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Answers from '../components/Answers';

const Container = styled.div`
  padding-top: 100px;
  width: 800px;
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 0 auto;
`;

const Questions = () => {
  const [board, setBoard] = useState({});
  const { boardId } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get(`/boards/${boardId}`, {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        if (res.data) {
          setBoard(res.data);
          console.log('보드아이디', boardId);
          console.log('태그 데이터', res.data.tagNames);
        }
        console.log(board);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  // useEffect(() => {
  //   console.log(board);
  // }, [board, setBoard]);

  if (!board) {
    return <div>로딩 중...</div>;
  }

  return (
    <Container>
      {board.title ? (
        <>
          <QuestionHeader
            title={board.title}
            createAt={board.viewCount}
            modifiedAt={board.viewCount}
            viewCount={board.viewCount}
          />
          <Question
            content={board.content}
            contentTry={board.contentTry}
            likeCount={board.likeCount}
            tagNames={board.tagNames}
          />
          <Answers />
          <AnswerForm boardId={boardId} />
        </>
      ) : (
        <p>Loading...</p>
      )}
    </Container>
  );
};

export default Questions;
