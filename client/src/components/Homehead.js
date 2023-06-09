import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Head = styled.div`
  margin-left: 30px;
  margin-right: 30px;

  > .top-head {
    display: flex;
    justify-content: space-between;

    > button {
      height: 38px;
      width: 120px;
      font-size: 15px;
      color: white;
      border-radius: 3px;
      border: 1.2px solid #0a95ff;
      background-color: #0a95ff;
      box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
      flex-shrink: 0;

      justify-content: center;
      align-items: center;

      &:hover {
        background-color: #006bb3;
        border: 1.2px solid #006bb3;
      }
      cursor: pointer;
    }
  }
  > .bottom-head {
    display: flex;
    justify-content: space-between;
    margin-top: 30px;
  }
`;

const QuestionNum = styled.div`
  font-size: 20px;
`;

const Buttons = styled.div`
  > button {
    height: 38px;
    width: 80px;
    background-color: white;
    border-radius: 2px;
    border: solid 1px #bdbdbd;
    cursor: pointer;
    text-align: center;
  }
`;

const Homehead = () => {
  const navigate = useNavigate();

  const handleAsk = () => {
    navigate('/questionsubmit');
  };

  const handleHot = () => {};
  const handleUn = () => {};

  return (
    <div>
      <Head>
        <div className="top-head">
          <h1>All Questions</h1>
          <button onClick={handleAsk}>Ask Question</button>
        </div>
        <div className="bottom-head">
          <QuestionNum>37questions</QuestionNum>
          <Buttons>
            <button>Newest</button>
            <button onClick={handleHot}>Hot</button>
            <button onClick={handleUn}>Unanswerd</button>
          </Buttons>
        </div>
      </Head>
    </div>
  );
};

export default Homehead;
