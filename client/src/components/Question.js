import React, { useState } from 'react';
import styled from 'styled-components';
import { TbTriangleInverted } from 'react-icons/tb';

const dummy = {
  title: 'How do I get the current date in typst?',
  createAt: '2020-02-20',
  modifiedAt: '2020-03-22',
  likeCount: 3,
  content: `I would like to insert the current date in the title section of my document. Is it even possible to do so yet? If so, how?
 
  Typst is fairly new, so here's a link to their homepage in case you hadn't heard of it yet: https://typst.app/
  
  As an aside, I figure that if typst is truly meant to become a LaTeX successor/alternative, we had better get started on matching all of the incredibly helpful Stack Exchange Q&A!
  `,
  tag: ['javassss', 'react'],
};

const dummyComment = [
  {
    number: '1',
    id: 'taeyoung',
    content:
      'Comment test test test~Comment test test test~Comment test test test~Comment test test test~',
  },
  {
    number: '2',
    id: 'miso',
    content: 'Comment test test test~',
  },
];

const Container = styled.div`
  padding-top: 100px;
  width: 700px;
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 0 auto;
`;

const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

const Title = styled.div`
  font-size: 35px;
`;
const DateContainer = styled.div`
  width: 100%;
  margin-top: 20px;
  display: flex;
  justify-content: flex-start;
`;

const CreatedAt = styled.div`
  flex: 1;
`;

const ModifiedAt = styled.div`
  flex: 1;
  margin-right: 350px;
`;

const Line = styled.div`
  border: 1px solid black;
  width: 100%;
  margin-top: 20px;
`;

const AskButton = styled.div`
  height: 38px;
  width: 100px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  margin-left: 5px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const LikeCountContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  font-size: 30px;
  height: 120px;
  color: gray;
  font-weight: 600;
  margin-left: 10px;
  margin-top: 30px;
`;

const LikeCount = styled(TbTriangleInverted)`
  font-size: 30px;
  color: #babfc4;
  transform: rotate(180deg);
`;

const DisLikeCount = styled(TbTriangleInverted)`
  font-size: 30px;
  color: #babfc4;
`;

const ContentContainer = styled.div`
  margin-left: 20px;
  margin-top: 30px;
  font-size: 20px;
  white-space: pre-wrap;
`;

const Body = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Tag = styled.div`
  color: #2c5877;
  display: inline-block;
  margin-right: 10px;
  height: 22px;
  padding: 5px;
  background-color: #d0e3f1;
  border-radius: 3px;
`;

const TagContainer = styled.div`
  margin-top: 10px;
`;
// ========================================================================
const AddComment = styled.div`
  margin-top: 30px;
  font-size: 16px;
  color: #888889;
  cursor: pointer;
`;

const CommentForm = styled.form`
  margin-top: 20px;
  display: ${(props) => (props.show ? 'block' : 'none')};
`;

const CommentTextArea = styled.textarea`
  width: 100%;
  height: 100px;
`;

const CommentButton = styled.button`
  height: 30px;
  width: 60px;
  font-size: 15px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

const CancleButton = styled.button`
  height: 30px;
  width: 60px;
  font-size: 15px;
  color: #0a95ff;
  border-radius: 3px;
  margin-left: 3px;
  border: 1.2px solid #0a95ff;
  background-color: white;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: #d0e3f1;
    border: 1.2px solid #0a95ff;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
`;

// ==============================================================

const AnswerContainer = styled.div`
  margin-top: 100px;
  width: 100%;
`;

const AnswerForm = styled.form``;

const Answer = styled.div`
  font-size: 25px;
`;

const AnswerTextArea = styled.textarea`
  margin-top: 12px;
  width: 100%;
  height: 130px;
`;

const AnswerButton = styled.button`
  height: 40px;
  width: 135px;
  font-size: 14px;
  color: white;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: #0a95ff;
  flex-shrink: 0;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);

  &:hover {
    background-color: #006bb3;
    border: 1.2px solid #006bb3;
  }
`;

//================================================================
const CommentListContainer = styled.div``;

const CommentLine = styled.div`
  border: 1px solid gray;
  width: 100%;
  margin-top: 20px;
`;

const CommentContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;

const CommentLineContent = styled.div`
  width: 100%;
`;

const CommentAround = styled.div`
  margin-top: 10px;
  display: flex;
  justify-content: flex-start;
`;

const CommentContent = styled.div`
  font-size: 16px;
`;

const CommentId = styled.div`
  margin-left: auto;
`;

const CommentNumber = styled.div`
  color: gray;
  width: 30px;
`;

//================================================================
//================================================================
const Question = () => {
  const [showCommentForm, setShowCommentForm] = useState(false);
  const [comment, setComment] = useState('');
  const [answer, setAnswer] = useState('');

  const handleAddCommentClick = () => {
    setShowCommentForm(true);
  };

  const handleCancelClick = () => {
    setShowCommentForm(false);
    setComment('');
  };

  const handleCommentSubmit = (e) => {
    e.preventDefault();
    console.log(comment);
    setShowCommentForm(false);
    setComment('');
    alert('등록되었습니다!');
  };

  const handleCommentChange = (e) => {
    setComment(e.target.value);
  };

  const handleAnswerSubmit = (e) => {
    e.preventDefault();
    console.log(answer);
    setAnswer('');
    alert('등록되었습니다!');
  };

  const handleAnswerChange = (e) => {
    setAnswer(e.target.value);
  };
  return (
    <Container>
      <TitleContainer>
        <Title>{dummy.title}</Title>
        <AskButton>Ask Question</AskButton>
      </TitleContainer>
      <DateContainer>
        <CreatedAt>Asked: {dummy.createAt}</CreatedAt>
        <ModifiedAt>Modified: {dummy.modifiedAt}</ModifiedAt>
      </DateContainer>
      <Line />
      <Body>
        <LikeCountContainer>
          <LikeCount />
          {dummy.likeCount}
          <DisLikeCount />
        </LikeCountContainer>
        <ContentContainer>
          {dummy.content}
          <TagContainer>
            {dummy.tag.map((tag) => (
              <Tag key={tag}>{tag}</Tag>
            ))}
          </TagContainer>
          {/*              코멘트              */}
          <CommentListContainer>
            {dummyComment.map((i) => (
              <CommentContainer key={i.id}>
                <CommentLineContent>
                  <CommentLine />
                  <CommentAround>
                    <CommentNumber>{i.number}</CommentNumber>
                    <CommentContent>{i.content}</CommentContent>
                    <CommentId>{i.id}</CommentId>
                  </CommentAround>
                </CommentLineContent>
              </CommentContainer>
            ))}
          </CommentListContainer>
          {/*              코멘트 추가            */}
          <AddComment onClick={handleAddCommentClick}>Add a comment</AddComment>
          <CommentForm show={showCommentForm} onSubmit={handleCommentSubmit}>
            <CommentTextArea
              value={comment}
              onChange={handleCommentChange}
              placeholder="댓글을 입력해주세요!"
              required
            />
            <ButtonContainer>
              <CommentButton type="submit">Submit</CommentButton>
              <CancleButton type="button" onClick={handleCancelClick}>
                Cancle
              </CancleButton>
            </ButtonContainer>
          </CommentForm>
        </ContentContainer>
      </Body>
      <AnswerContainer>
        <Answer>Your Answer</Answer>
        <AnswerForm onSubmit={handleAnswerSubmit}>
          <AnswerTextArea
            value={answer}
            onChange={handleAnswerChange}
            required
          />
          <AnswerButton type="submit">Post Your Answer</AnswerButton>
        </AnswerForm>
      </AnswerContainer>
    </Container>
  );
};

export default Question;
