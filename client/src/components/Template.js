import React from 'react';
import HemeList from './HomeList';
import styled from 'styled-components';

const Page = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-end;
  margin-top: 20px;
  border-top: 1px solid #dbdcdd;

  > span {
    padding-right: 10px;
    margin-bottom: 30px;
    margin-top: 50px;
  }
`;

const Template = () => {
  return (
    <>
      <HemeList />
      <Page>
        <span>1</span>
        <span>next</span>
      </Page>
    </>
  );
};

export default Template;
