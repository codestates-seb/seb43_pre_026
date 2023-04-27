import React, { useState, useEffect } from 'react';
import Question from '../components/Question';
import styled from 'styled-components';
import AnswerForm from '../components/AnswerForm';
import QuestionHeader from '../components/QuestionHeader';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Answers from '../components/Answers';
import Header from '../components/Header';

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
  const [filteredAnswer, setFilteredAnswer] = useState({});
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get(
          `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/boards/${boardId}`
        );
        if (res.data) {
          setBoard(res.data);

          const filteredAnswer = AnswerFilter(res.data.answers);
          setFilteredAnswer(filteredAnswer);
        }
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  const AnswerFilter = (answersData) => {
    const answer = [];
    const answerComment = [];

    answersData.forEach((answersData) => {
      if (answersData.parentId === 0) {
        answer.push(answersData);
      } else {
        answerComment.push(answersData);
      }
    });
    return { answer, answerComment };
  };

  if (!board) {
    return <div>로딩 중...</div>;
  }
  return (
    <>
      <Header />
      <Container>
        {board.title ? (
          <>
            <QuestionHeader
              title={board.title}
              createAt={board.createdAt}
              modifiedAt={board.modifiedAt}
              viewCount={board.viewCount}
            />
            <Question
              content={board.content}
              contentTry={board.contentTry}
              likeCount={board.voteCount}
              tagNames={board.tagNames}
              memberNickname={board.memberNickname}
              createdAt={board.createdAt}
              userImg={board.memberNickname}
              memberId={memberId}
              boardId={board.boardId}
              comments={board.comments}
            />
            <Answers
              filteredAnswer={filteredAnswer}
              answerId={filteredAnswer.answer.answerId}
            />
            <AnswerForm boardId={board.boardId} />
          </>
        ) : (
          <p>Loading...</p>
        )}
      </Container>
    </>
  );
};

export default Questions;
