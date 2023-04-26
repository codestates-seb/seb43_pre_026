import styled from 'styled-components';

const Container = styled.div`
  margin: 0 auto;
`;

const Addition = styled.div`
  font-size: 15px;
  font-weight: normal;
  color: hsl(210, 8%, 25%);
  cursor: text;
  margin-top: 5px;
`;

const Tag = styled.div`
  width: 800px;
  height: auto;
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

const Input = styled.input`
  height: 40px;
  width: 100%;
  margin-top: 15px;
  border: 1px solid #cacaca;
  :focus {
    outline: none;
    border-color: skyblue;
    box-shadow: 0 0 10px skyblue;
  }
`;
const TagList = styled.ul`
  padding: 0;
  margin: 8px 0 0 0;
  display: flex;
  flex-wrap: wrap;
  width: auto;
`;
const TagItem = styled.li`
  border: none;
  border-radius: 5px;
  background-color: hsl(205, 46%, 92%);

  width: auto;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  margin-right: 7px;
  margin-bottom: 5px;
  list-style: none;

  display: flex;
  align-items: center;
  justify-content: center;

  font-size: 15px;
  font-weight: normal;
`;
const TagText = styled.span`
  color: hsl(205, 47%, 42%);
`;
const TagCloseIcon = styled.span`
  display: block;
  width: 16px;
  height: 16px;
  line-height: 16px;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
  margin-left: 8px;
  color: hsl(205, 47%, 42%);
  border-radius: 50%;
  background: white;
  cursor: pointer;
`;

const TagInput = ({ tags, setTags }) => {
  const removeTags = (indexToRemove) => {
    const filter = tags.filter((el, index) => index !== indexToRemove);
    setTags(filter);
  };

  const addTags = (event) => {
    if (
      event.key === 'Enter' &&
      event.target.value !== '' &&
      !tags.includes(event.target.value)
    ) {
      setTags([...tags, event.target.value]);
      event.target.value = '';
    }
  };

  return (
    <Container>
      <Tag>
        <div>Tags</div>
        <Addition>
          Add up to 5 tags to describe what your question is about. Start typing
          to see suggestions.
        </Addition>
        <>
          <TagList>
            {tags.map((tag, index) => (
              <TagItem key={index}>
                <TagText>{tag}</TagText>
                <TagCloseIcon onClick={() => removeTags(index)}>
                  &times;
                </TagCloseIcon>
              </TagItem>
            ))}
          </TagList>
          <Input
            placeholder="e.g. (node.js python .net)"
            type="text"
            onKeyUp={(e) => addTags(e)}
          />
        </>
      </Tag>
    </Container>
  );
};

export default TagInput;
