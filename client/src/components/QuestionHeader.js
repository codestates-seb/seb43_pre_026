import React from 'react';
// import { dummy } from './Question';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';

const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

const Title = styled.div`
  font-size: 35px;
`;
const DateContainer = styled.div`
  width: 60%;
  margin-right: auto;
  margin-top: 20px;
  display: flex;
  justify-content: leㅈft;
`;

const CreatedAt = styled.div`
  flex: 1;
`;

const ModifiedAt = styled.div`
  flex: 1;
`;

const ViewCount = styled.div`
  flex: 1;
`;

const Line = styled.div`
  border: 1px solid #888889;
  width: 100%;
  margin-top: 20px;
`;

const AskButton = styled.div`
  height: 38px;
  width: 100px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  margin-left: 5px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const QuestionHeader = ({ title, createAt, modifiedAt, viewCount }) => {
  const navigate = useNavigate();

  const handleAsk = () => {
    navigate('/questionsubmit');
  };

  console.log('퀘스천 헤더!', title);

  return (
    <>
      <TitleContainer>
        <Title>{title}</Title>
        <AskButton onClick={handleAsk}>Ask Question</AskButton>
      </TitleContainer>
      <DateContainer>
        <CreatedAt>Asked: {createAt}</CreatedAt>
        <ModifiedAt>Modified: {modifiedAt}</ModifiedAt>
        <ViewCount>Views:{viewCount}</ViewCount>
      </DateContainer>
      <Line />
    </>
  );
};
QuestionHeader.propTypes = {
  title: PropTypes.string.isRequired,
  createAt: PropTypes.string.isRequired,
  modifiedAt: PropTypes.string.isRequired,
  viewCount: PropTypes.number.isRequired,
};

export default QuestionHeader;
