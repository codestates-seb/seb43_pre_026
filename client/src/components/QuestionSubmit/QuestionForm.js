import styled from 'styled-components';
import TitleInput from '../QuestionSubmit/TitleInput';
import ProblemInput from '../QuestionSubmit/ProblemInput';
import TriedInput from '../QuestionSubmit/TriedInput';
import TagInput from '../QuestionSubmit/TagInput';
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
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
  }

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

    const postData = async () => {
      try {
        const response = await axios.post('/boards', {
          memberId: memberId,
          title: title,
          content: problem,
          contentTry: tried,
          tagNames: tags.map((tag) => ({ tagName: tag })),
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        setTitle(response.data.title);
        setProblem(response.data.content);
        setTried(response.data.contentTry);
        setTags(response.data.tagNames);
      } catch (error) {
        console.error(error);
      }
    };
    postData();
    alert('질문이 등록되었습니다.');
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
