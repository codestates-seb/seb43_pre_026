import React, { useState } from 'react';
import styled from 'styled-components';
import { TbTriangleInverted } from 'react-icons/tb';

export const dummy = {
  title: 'How do I get the current date in typst?',
  createAt: '2020-02-20',
  modifiedAt: '2020-03-22',
  likeCount: 3,
  content: `I would like to insert the current date in the title section of my document. Is it even possible to do so yet? If so, how?
 
  Typst is fairly new, so here's a link to their homepage in case you hadn't heard of it yet: https://typst.app/
  
  As an aside, I figure that if typst is truly meant to become a LaTeX successor/alternative, we had better get started on matching all of the incredibly helpful Stack Exchange Q&A!
  `,
  tag: ['java', 'react', 'cors'],
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

const LikeCountContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  font-size: 30px;
  height: 120px;
  color: gray;
  font-weight: 600;
  margin-left: 5px;
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
  font-size: 15px;
`;

const CommentId = styled.div`
  font-size: 17px;
  font-weight: 500;
  margin-left: auto;
`;

const CommentNumber = styled.div`
  color: gray;
  width: 25px;
  font-size: 17px;
`;

const Question = () => {
  const [showCommentForm, setShowCommentForm] = useState(false);
  const [comment, setComment] = useState('');

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

  return (
    <>
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
    </>
  );
};
export default Question;
