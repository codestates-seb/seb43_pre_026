import React from 'react';
import styled from 'styled-components';
import HomeItemInfo from './HomeItemInfo';

const Container = styled.div`
  width: 800px;
  height: 150px;
  border-top: solid 1px #dbdcdd;

  margin: 0 auto;
  margin-top: 20px;
  padding-top: 10px;
  padding-bottom: 10px;

  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
`;

const ItemBody = styled.div`
  display: flex;
  flex-direction: row;
  margin-left: 20px;
  margin-right: 20px;
`;

const List = styled.div`
  display: flex;
  height: 90px;
  padding-top: 30px;
  flex-direction: column;
  color: gray;
  > .vote {
    width: 80px;
  }
`;

const Tags = styled.div`
  width: 1000px;
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  margin-left: 126px;
  margin-right: 30px;

  > .tags span {
    border: none;
    border-radius: 5px;
    background-color: hsl(205, 46%, 92%);
    color: hsl(205, 47%, 42%);

    width: auto;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    margin-right: 7px;
  }
  > .author {
    align-items: flex-end;
  }
`;

const HomeItem = () => {
  return (
    <Container>
      <ItemBody>
        <List>
          <div className="vote">65 votes</div>
          <div className="answers">8 answers</div>
        </List>
        <HomeItemInfo />
      </ItemBody>
      <Tags>
        <div className="tags">
          <span>javascript</span>
          <span>css</span>
          <span>html</span>
        </div>
        <div className="author">username</div>
      </Tags>
    </Container>
  );
};

export default HomeItem;
