import axios from 'axios';
import React, { useState } from 'react';
import styled from 'styled-components';

const AddComment = styled.div`
  margin-top: 10px;
  font-size: 16px;
  color: #888889;
  cursor: pointer;
`;

const CommentForm = styled.form`
  margin-top: 20px;
  display: ${(props) => (props.show ? 'block' : 'none')};
`;

const CommentTextArea = styled.textarea`
  width: 700px;
  height: 80px;
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

const AnswerComment = () => {
  const [showCommentForm, setShowCommentForm] = useState(false);
  const [comment, setComment] = useState('');

  const handleAddCommentClick = () => {
    setShowCommentForm(!showCommentForm);
  };

  const handleCancelClick = () => {
    setShowCommentForm(false);
    setComment('');
  };

  const handleCommentSubmit = (e) => {
    e.preventDefault();
    setShowCommentForm(false);
    setComment('');

    axios.post('/answer', {
      comment,
    });

    alert('등록되었습니다!');
  };

  const handleCommentChange = (e) => {
    setComment(e.target.value);
  };

  return (
    <div>
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
    </div>
  );
};

export default AnswerComment;
