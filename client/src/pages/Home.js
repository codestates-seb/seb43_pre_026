import React from 'react';
import Template from '../components/Template';
import Homehead from '../components/Homehead';
import styled from 'styled-components';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
`;

const Home = () => {
  return (
    <>
      <Container>
        <Homehead />
        <Template />
      </Container>
    </>
  );
};

export default Home;
