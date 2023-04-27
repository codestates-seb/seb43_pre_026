import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import SearchHeader from '../components/SearchHeader';
import SearchList from '../components/SearchList';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import Header from '../components/Header';

const Container = styled.div`
  width: 800px;
  margin: 0 auto;
  padding-top: 70px;
`;

const Search = () => {
  const { search } = useLocation();
  const params = new URLSearchParams(search);
  const searchDataDivide = params.toString().split('=');

  const [boards, setBoards] = useState([]);
  const selectedOption = searchDataDivide[0];
  const inputValue = searchDataDivide[1];

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get(
          `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/boards/list?${selectedOption}=${inputValue}`
        );
        setBoards(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    getData();
  }, []);

  return (
    <div>
      <Header />
      <Container>
        <SearchHeader boardsLength={boards.length} />
        <SearchList boards={boards} />
      </Container>
    </div>
  );
};

export default Search;
