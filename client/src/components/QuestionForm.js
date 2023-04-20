import React from 'react';
import styled from 'styled-components';

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

const Problem = styled(Title)`
  height: 242px;
`;

const Try = styled(Title)`
  height: 242px;
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
`;

const Textarea = styled.textarea`
  height: 155px;
  margin-top: 15px;
  border: 1px solid #cacaca;
`;

const Tag = styled(Title)``;

const Button = styled.button`
  height: 38px;
  width: 150px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;

  justify-content: center;
  align-items: center;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
  cursor: pointer;
`;

const QuestionForm = () => {
  return (
    <Container>
      <Title>
        <div>Title</div>
        <Addition>
          Be specific and imagine you’re asking a question to another person.
        </Addition>
        <Input />
      </Title>
      <Problem>
        <div>What are the details of your problem?</div>
        <Addition>
          Introduce the problem and expand on what you put in the title. Minimum
          20 characters.
        </Addition>
        <Textarea />
      </Problem>
      <Try>
        <div>What did you try and what were you expecting?</div>
        <Addition>
          Describe what you tried, what you expected to happen, and what
          actually resulted. Minimum 20 characters.
        </Addition>
        <Textarea />
      </Try>
      <Tag>
        <div>Tags</div>
        <Addition>
          Add up to 5 tags to describe what your question is about. Start typing
          to see suggestions.
        </Addition>
        <Input />
      </Tag>
      <Button>post your question</Button>
    </Container>
  );
};

export default QuestionForm;
