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
  margin-top: 5px;
  margin-bottom: 5px;
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

const LikeCount = ({ likeCount, boardId }) => {
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
    axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
  }

  const handleLike = () => {
    axios
      .patch(
        `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/boards/vote`,
        {
          memberId: memberId,
          boardId,
          boardVoteCount: 1,
        }
      )
      .catch((error) => {
        console.error(error);
      });
  };

  const handleDisLike = () => {
    axios
      .patch(`boards/vote`, {
        memberId: memberId,
        boardId,
        boardVoteCount: 0,
      })
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

LikeCount.propTypes = {
  likeCount: PropTypes.number.isRequired,
  boardId: PropTypes.number.isRequired,
};
export default LikeCount;
