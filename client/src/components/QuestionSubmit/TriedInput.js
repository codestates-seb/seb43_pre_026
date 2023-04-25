import React, { forwardRef } from 'react';
import styled from 'styled-components';

const Container = styled.div`
  margin: 0 auto;
`;

const Try = styled.div`
  height: 242px;
  width: 800px;
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

const Textarea = styled.textarea`
  height: 155px;
  margin-top: 15px;
  border: 1px solid #cacaca;
  resize: none;

  :focus {
    outline: none;
    border-color: skyblue;
    box-shadow: 0 0 10px skyblue;
  }
`;

const Addition = styled.div`
  font-size: 15px;
  font-weight: normal;
  color: hsl(210, 8%, 25%);
  cursor: text;
  margin-top: 5px;
`;

const TriedInput = forwardRef((props, ref) => {
  const handleTried = (e) => {
    props.setTried(e.target.value);
  };

  return (
    <Container>
      <Try>
        <div>What did you try and what were you expecting?</div>
        <Addition>
          Describe what you tried, what you expected to happen, and what
          actually resulted. Minimum 20 characters.
        </Addition>
        <Textarea
          type="text"
          value={props.tried}
          onChange={handleTried}
          ref={ref}
        />
      </Try>
    </Container>
  );
});

TriedInput.displayName = 'TriedInput';

export default TriedInput;
