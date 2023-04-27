import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
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

const HomeItemInfo = () => {
  return (
    <Container>
      <h3>Should an application on a local network run on HTTPS?</h3>
      <div className="content">
        I have a Java web application (Tomcat) that runs on HTTP (not HTTPS) on
        a local network and is not accessible from the Internet. Only from local
        network, restricted by firewall. Apache running on ...
      </div>
    </Container>
  );
};

export default HomeItemInfo;
