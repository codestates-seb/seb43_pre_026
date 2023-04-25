import styled from 'styled-components';
import TitleInput from './TitleInput';
import ProblemInput from './ProblemInput';
import TriedInput from './TriedInput';
import TagInput from './TagInput';
import { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Container = styled.div`
  margin: 0 auto;
`;

const Button = styled.button`
  display: flex;
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
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [problem, setProblem] = useState('');
  const [tried, setTried] = useState('');
  const [tags, setTags] = useState([]);

  const titleFocus = useRef(null);
  const problemFocus = useRef(null);
  const triedFocus = useRef(null);

  const handleSubmit = () => {
    if (title.length < 1) {
      titleFocus.current.focus();
      return;
    }
    if (problem.length < 20) {
      problemFocus.current.focus();
      return;
    }
    if (tried.length < 20) {
      triedFocus.current.focus();
      return;
    }
    //console.log({ title, problem, tried, tags });

    const postData = async () => {
      try {
        const response = await axios.post('boards', {
          //memberId: `${id}`,
          title: title,
          content: problem,
          contentTry: tried,
          tagNames: tags.map((tag) => ({ tagName: tag })),
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        setTitle(response.data);
        setProblem(response.data);
        setTried(response.data);
        setTags(response.data);

        //console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    postData();
    navigate('/');
  };

  return (
    <Container>
      <TitleInput title={title} setTitle={setTitle} ref={titleFocus} />
      <ProblemInput
        problem={problem}
        setProblem={setProblem}
        ref={problemFocus}
      />
      <TriedInput tried={tried} setTried={setTried} ref={triedFocus} />
      <TagInput tags={tags} setTags={setTags} />
      <>
        <Button onClick={handleSubmit}>post your question</Button>
      </>
    </Container>
  );
};

export default QuestionForm;
