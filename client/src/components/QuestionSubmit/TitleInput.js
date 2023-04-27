import React, { forwardRef } from 'react';
import styled from 'styled-components';
/* eslint-disable react/prop-types */

const Container = styled.div`
  margin: 0 auto;
`;

const Title = styled.div`
  width: 800px;
  height: 126px;
  border: 1px solid #dbdcdd;
  margin-bottom: 20px;
  padding: 20px;
  padding-bottom: 10px;
  font-weight: bold;
  font-size: 20px;

  display: flex;
  flex-direction: column;
  background-color: white;
`;

const Addition = styled.div`
  font-size: 15px;
  font-weight: normal;
  color: hsl(210, 8%, 25%);
  cursor: text;
  margin-top: 5px;
`;

const Input = styled.input`
  height: 40px;
  margin-top: 15px;
  border: 1px solid #cacaca;
  :focus {
    outline: none;
    border-color: skyblue;
    box-shadow: 0 0 10px skyblue;
  }
`;

const TitleInput = forwardRef((props, ref) => {
  const handleTitle = (e) => {
    props.setTitle(e.target.value);
  };

  return (
    <Container>
      <Title>
        <div>Title</div>
        <Addition>
          Be specific and imagine you’re asking a question to another person.
        </Addition>
        <Input
          type="text"
          placeholder="e.g Is there an R function for finding the index of an element in a vector?"
          value={props.title}
          onChange={handleTitle}
          ref={ref}
        />
      </Title>
    </Container>
  );
});

TitleInput.displayName = 'TitleInput';

export default TitleInput;
