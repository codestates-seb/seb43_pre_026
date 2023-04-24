import React, { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Container = styled.div`
  width: 800px;
  height: 100%;
  border-top: solid 1px #dbdcdd;
  margin: 0 auto;
  margin-top: 20px;
  padding-top: 10px;
  padding-bottom: 10px;
  display: flex;
  /* flex-direction: row; */
`;

const VoteViewContainer = styled.div`
  display: flex;
  margin-left: 10px;
  padding-top: 30px;
  flex-direction: column;

  /* margin-right: 20px; */
`;

const Votes = styled.div`
  display: flex;
  color: black;
  margin-bottom: 10px;
`;

const Views = styled.div`
  display: flex;
  color: #575757;
  margin-bottom: 10px;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 30px;
`;

const TagContainer = styled.div`
  display: flex;
  margin-top: 5px;
`;

const Tag = styled.div`
  border: none;
  border-radius: 5px;
  background-color: hsl(205, 46%, 92%);
  color: hsl(205, 47%, 42%);
  width: auto;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  margin-right: 7px;
`;

const Title = styled.div`
  color: #2b73cc;
  font-size: 21px;
  margin-top: 10px;
  margin-bottom: 15px;

  &:hover {
    color: #0a95ff;
    cursor: pointer;
  }
`;
const Content = styled.div`
  /* white-space: pre-wrap; */
  width: 650px;
  display: block;
  word-break: break-all;
`;

function BoardList() {
  const [boards, setBoards] = useState([]);
  // const { searchData } = useParams

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get('/boards', {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        setBoards(response.data);
        // console.log(1, response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  const navigate = useNavigate();

  const handleToQuestion = (id) => {
    navigate(`/boards/${id}`);
  };

  return (
    <>
      {boards.length > 0 ? (
        <>
          {boards.map((board) => (
            <Container key={board.boardId}>
              <VoteViewContainer>
                <Votes>{board.likeCount} votes</Votes>
                <Views> {board.viewCount} views</Views>
              </VoteViewContainer>

              <ContentContainer>
                <Title onClick={() => handleToQuestion(board.boardId)}>
                  {board.title}
                </Title>
                <Content>
                  {board.content.slice(0, 200) +
                    (board.content.length > 200 ? '...' : '')}
                </Content>

                <TagContainer>
                  {board.tagNames.map((tag) => (
                    <Tag key={tag.tagId}>{tag.tagName}</Tag>
                  ))}
                </TagContainer>
              </ContentContainer>
            </Container>
          ))}
        </>
      ) : (
        <p>Loading...</p>
      )}
    </>
  );
}

export default BoardList;

// import React, { useState, useEffect } from 'react';
// import styled from 'styled-components';
// import axios from 'axios';

// const Container = styled.div`
//   width: 800px;
//   height: 150px;
//   border-top: solid 1px #dbdcdd;

//   margin: 0 auto;
//   margin-top: 20px;
//   padding-top: 10px;
//   padding-bottom: 10px;

//   display: flex;
//   flex-direction: row;
//   flex-wrap: wrap;
// `;

// const ItemBody = styled.div`
//   display: flex;
//   flex-direction: row;
//   margin-left: 20px;
//   margin-right: 20px;
// `;

// const List = styled.div`
//   display: flex;
//   height: 90px;
//   padding-top: 30px;
//   flex-direction: column;
//   color: gray;
//   > .vote {
//     width: 80px;
//   }
// `;

// const Tags = styled.div`
//   width: 1000px;
//   display: flex;
//   justify-content: space-between;
//   margin-top: 20px;
//   margin-left: 126px;
//   margin-right: 30px;

//   > .tags span {
//     border: none;
//     border-radius: 5px;
//     background-color: hsl(205, 46%, 92%);
//     color: hsl(205, 47%, 42%);

//     width: auto;
//     padding-left: 10px;
//     padding-right: 10px;
//     padding-top: 5px;
//     padding-bottom: 5px;
//     margin-right: 7px;
//   }
//   > .author {
//     align-items: flex-end;
//   }
// `;

// const ContentContainer = styled.div`
//   display: flex;
//   flex-direction: column;
//   margin-left: 30px;
// `;
// const Title = styled.div`
//   flex-wrap: wrap;
//   color: #5393f8;
//   margin-bottom: 10px;
// `;
// const Content = styled.div`
//   font-size: 17px;
//   flex-wrap: wrap;
// `;

// const HomeItem = () => {
//   const [boardData, setBoardData] = useState([]);
//   const { boardId, title, content, likeCount, viewCount, tagNames } = boardData;

//   useEffect(() => {
//     const getData = async () => {
//       try {
//         const response = await axios.get('http://localhost:3003/boards');
//         setBoardData(response.data);
//         console.log(response.data);
//       } catch (error) {
//         console.log(error);
//       }
//     };
//     getData();
//   }, []);

//   return (
//     <Container>
//       {boards.map((board) => (

//       <ItemBody>
//         <List>
//           <div className="vote">vote: {board.likeCount}</div>
//           <div className="answers">answers{board.viewCount}</div>
//         </List>
//         <ContentContainer>
//           <Title>{board.title}</Title>
//           <Content>{board.content}</Content>
//         </ContentContainer>
//       </ItemBody>
//       <Tags>
//         <div className="tags">
//         {board.tagNames.map((tag) => (
//                   <li key={tag.tagId}>{tag.tagName}</li>
//                 ))}
//         </div>
//         <div className="author">{board.oardId}</div>
//       </Tags>
//       ))}
//     </Container>
//   );
// };

// export default HomeItem;
