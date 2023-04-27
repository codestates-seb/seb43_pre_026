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
          `boards/list?${selectedOption}=${inputValue}`,
          {
            headers: {
              'ngrok-skip-browser-warning': '69420',
            },
          }
        );
        setBoards(response.data);
      } catch (error) {
        console.log(error);
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
