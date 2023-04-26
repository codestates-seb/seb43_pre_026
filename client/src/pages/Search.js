import React from 'react';
import styled from 'styled-components';
import SearchHeader from '../components/SearchHeader';
import SearchList from '../components/SearchList';
import { useLocation } from 'react-router-dom';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
`;

const Search = () => {
  const { search } = useLocation();
  const params = new URLSearchParams(search);
  const searchDataDivide = params.toString().split('=');

  return (
    <div>
      <Container>
        <SearchHeader />
        <SearchList searchDataDivide={searchDataDivide} />
      </Container>
    </div>
  );
};

export default Search;
