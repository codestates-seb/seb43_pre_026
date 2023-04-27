import React from 'react';
import styled from 'styled-components';
import { TbTriangleInverted } from 'react-icons/tb';
import PropTypes from 'prop-types';
import axios from 'axios';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  font-size: 30px;
  height: 120px;
  color: gray;
  font-weight: 600;
  margin-left: 5px;
  margin-top: 30px;
`;

const Like = styled(TbTriangleInverted)`
  font-size: 30px;
  color: #babfc4;
  transform: rotate(180deg);
  cursor: pointer;
`;

const DisLike = styled(TbTriangleInverted)`
  font-size: 30px;
  color: #babfc4;
  cursor: pointer;
`;

const AnswerLikeCount = ({ likeCount, answerId }) => {
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
  }

  const handleLike = () => {
    axios
      .patch(
        `/answers/vote`,
        {
          memberId: memberId,
          answerId,
          answerVote: 1,
        },
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .catch((error) => {
        console.error(error);
      });
  };

  const handleDisLike = () => {
    axios
      .patch(
        `/answers/vote`,
        {
          memberId: memberId,
          answerId,
          answerVote: 0,
        },
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <Container>
      <Like onClick={handleLike} />
      {likeCount}
      <DisLike onClick={handleDisLike} />
    </Container>
  );
};

AnswerLikeCount.propTypes = {
  likeCount: PropTypes.number.isRequired,
  answerId: PropTypes.number.isRequired,
};
export default AnswerLikeCount;