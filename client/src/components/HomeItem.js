import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  width: 1000px;
  height: 150px;
  border-top: solid gray 1px;

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
  padding-top: 20px;
  flex-direction: column;
  justify-content: space-between;
  color: gray;
  > .vote {
    width: 80px;
  }
`;

const Preview = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 30px;

  > h3 {
    flex-wrap: wrap;
    color: #5393f8;
    margin-bottom: 10px;
  }
  > .content {
    font-size: 17px;
    flex-wrap: wrap;
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
        <Preview>
          <h3>Should an application on a local network run on HTTPS?</h3>
          <div className="content">
            I have a Java web application (Tomcat) that runs on HTTP (not HTTPS)
            on a local network and is not accessible from the Internet. Only
            from local network, restricted by firewall. Apache running on ...
          </div>
        </Preview>
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
