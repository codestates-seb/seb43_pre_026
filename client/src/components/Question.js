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

const Question = ({ content, contentTry, likeCount, tagNames }) => {
  return (
    <>
      <Body>
        <LikeCount likeCount={likeCount} />
        <ContentContainer>
          <div>{content}</div>
          <div>{contentTry}</div>
          <TagContainer>
            {tagNames.map(({ tagId, tagName }) => (
              <Tag key={tagId}>{tagName}</Tag>
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
          <Comment />
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
};

export default Question;
