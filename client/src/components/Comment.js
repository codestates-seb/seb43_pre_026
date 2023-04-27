import axios from 'axios';
import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

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

const Comment = ({ memberId, boardId }) => {
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
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
    }

    axios
      .post(
        'http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/answer',
        {
          comment,
          memberId,
          boardId,
          answerCheck: false,
        }
      )
      .then(() => {
        alert('댓글이 등록 되었습니다!');
      })
      .catch((error) => {
        console.error(error);
        alert('오류 발생!');
      });
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

Comment.propTypes = {
  memberId: PropTypes.number.isRequired,
  boardId: PropTypes.number.isRequired,
};

export default Comment;
