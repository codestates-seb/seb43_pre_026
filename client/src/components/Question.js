import React from 'react';
import styled from 'styled-components';
import LikeCount from './LikeCount';
import Comment from './Comment';
import PropTypes from 'prop-types';

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

// const dummyComment = [
//   {
//     number: '1',
//     id: 'taeyoung',
//     content:
//       'Comment test test test~Comment test test test~Comment test test test~Comment test test test~',
//   },
//   {
//     number: '2',
//     id: 'miso',
//     content: 'Comment test test test~',
//   },
// ];

export const answer = [
  {
    number: '5',
    id: 'taeyoung1',
    content:
      'Comment test test test~Comment test test test~Comment test test test~Comment test test test~',
    likeCount: 5,
  },
];

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

const CommentList = styled.div`
  margin-top: 20px;
  width: 92%;
`;

const CommentLine = styled.div`
  border: 1px solid #e3e6e8;
  width: 100%;
`;
const CommentContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;

const LineContent = styled.div`
  width: 100%;
`;

const CommentAround = styled.div`
  display: flex;
  align-items: center;
`;

const CommentContent = styled.div`
  font-size: 15px;
  margin-top: 7px;
  margin-left: 15px;
  margin-bottom: 7px;
  color: #0d0d0d;
`;

const CommentId = styled.div`
  font-size: 17px;
  font-weight: 500;
  color: #0a95ff;
  padding-left: 3px;
`;
// const CommentNumber = styled.div`
//   color: gray;
//   width: 25px;
//   font-size: 17px;
// `;

const UserProfile = styled.div`
  width: 200px;
  height: 70px;
  background-color: #d9eaf7;
  border-radius: 3px;
  margin-left: auto;
`;

const AskTime = styled.div`
  font-size: 15px;
  padding-top: 5px;
  padding-left: 5px;
`;
const UserImg = styled.div``;
const UserName = styled.div``;

const Question = ({
  content,
  contentTry,
  likeCount,
  tagNames,
  memberNickname,
  createdAt,
  userImg,
  memberId,
  boardId,
  comments,
}) => {
  const date = new Date(createdAt);
  const formattedDate = date.toLocaleString('en-KR', {
    month: 'short',
    day: 'numeric',
    hour: 'numeric',
    minute: '2-digit',
    hour12: false,
  });

  return (
    <>
      <Body>
        <LikeCount likeCount={likeCount} boardId={boardId} />
        <ContentContainer>
          <div>{content}</div>
          <div>{contentTry}</div>
          <TagContainer>
            {tagNames.map(({ tagId, tagName }) => (
              <Tag key={tagId}>{tagName}</Tag>
            ))}
          </TagContainer>
          <UserProfile>
            <AskTime>asked: {formattedDate}</AskTime>
            <UserImg>{userImg}</UserImg>
            <UserName>{memberNickname}</UserName>
          </UserProfile>
          {/*              코멘트              */}
          <CommentList>
            {comments.map((comment) => (
              <CommentContainer key={comment.answerId}>
                <LineContent>
                  <CommentLine />
                  <CommentAround>
                    <CommentContent>{comment.content} -</CommentContent>
                    <CommentId>{comment.memberNickname}</CommentId>
                  </CommentAround>
                </LineContent>
              </CommentContainer>
            ))}
          </CommentList>
          <Comment memberId={memberId} boardId={boardId} />
        </ContentContainer>
      </Body>
    </>
  );
};

Question.propTypes = {
  content: PropTypes.string.isRequired,
  contentTry: PropTypes.string.isRequired,
  likeCount: PropTypes.number.isRequired,
  tagNames: PropTypes.array.isRequired,
  memberNickname: PropTypes.string.isRequired,
  createdAt: PropTypes.string.isRequired,
  userImg: PropTypes.string.isRequired,
  memberId: PropTypes.number.isRequired,
  boardId: PropTypes.number.isRequired,
  comments: PropTypes.array.isRequired,
};

export default Question;
