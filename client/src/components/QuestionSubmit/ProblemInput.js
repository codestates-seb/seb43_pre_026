import styled from 'styled-components';
import React, { forwardRef } from 'react';

const Container = styled.div`
  margin: 0 auto;
`;

const Problem = styled.div`
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

const ProblemInput = forwardRef((props, ref) => {
  const handleProblem = (e) => {
    props.setProblem(e.target.value);
  };

  return (
    <Container>
      <Problem>
        <div>What are the details of your problem?</div>
        <Addition>
          Introduce the problem and expand on what you put in the title. Minimum
          20 characters.
        </Addition>
        <Textarea
          type="text"
          value={props.problem}
          onChange={handleProblem}
          ref={ref}
        />
      </Problem>
    </Container>
  );
});

ProblemInput.displayName = 'ProblemInput';

export default ProblemInput;
