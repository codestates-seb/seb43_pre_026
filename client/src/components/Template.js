import React from 'react';
import HomeList from './HomeList';
import styled from 'styled-components';

const Container = styled.div`
  height: auto;
  width: 1000px;
  margin-left: 345px;
  margin-right: 345px;
  border-left: solid gray 1px;
  border-right: solid gray 1px;
`;
const Head = styled.div`
  margin-left: 30px;
  margin-right: 30px;

  > .top-head {
    display: flex;
    justify-content: space-between;

    > h1 {
    }
    > button {
      width: 100px;
      height: 30px;

      background-color: hsl(206, 100%, 52%);
      color: white;
      border: #73adfa;
      border-radius: 5px;
      &:hover {
        background-color: hsl(206, 100%, 40%);
        color: white;
      }
      cursor: text;
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
    height: 30px;
    width: 80px;
    background-color: white;
    border-radius: 5px;
    border: solid 1px #bdbdbd;

    text-align: center;
  }
`;

const Tail = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-end;
  margin: 100px;
  > span {
    padding-right: 10px;
  }
`;

const Template = () => {
  return (
    <Container>
      <Head>
        <div className="top-head">
          <h1>All Questions</h1>
          <button>Ask Question</button>
        </div>
        <div className="bottom-head">
          <QuestionNum>37questions</QuestionNum>
          <Buttons>
            <button>Newest</button>
            <button>Hot</button>
            <button>Unanswerd</button>
          </Buttons>
        </div>
      </Head>
      <HomeList />
      <Tail>
        <span>1</span>
        <span>next</span>
      </Tail>
    </Container>
  );
};

export default Template;
