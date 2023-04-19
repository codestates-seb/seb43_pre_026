import React from 'react';
import styled from 'styled-components';
import { BiMessageAltError } from 'react-icons/bi';
import { TiArrowUnsorted } from 'react-icons/ti';
import { BsClipboardHeartFill, BsTrophy } from 'react-icons/bs';

const SignupText = () => {
  const Wrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
  `;

  const Text = styled.div`
    font-size: 30px;
    font-weight: 500;
    margin: 20px;
    text-align: left;
  `;
  const Subtext = styled.div`
    font-size: 18px;
    margin: 15px;
    text-align: left;
    display: flex;
    align-items: center;
  `;

  const Endtext = styled.div`
    display: flex;
    flex-direction: column;

    margin: 17px;
    font-size: 14.5px;
  `;

  const Grey = styled.div`
    color: #605f5f;
  `;

  const Blue = styled.div`
    color: #1a6ece;
  `;

  return (
    <Wrapper>
      <Text>Join the Stack Overflow community</Text>
      <Subtext>
        <BiMessageAltError size="35px" color="#3895FF" />
        &nbsp;Get unstuck — ask a question
      </Subtext>
      <Subtext>
        <TiArrowUnsorted size="33px" color="#3895FF" />
        &nbsp; Unlock new privileges like voting and commenting
      </Subtext>
      <Subtext>
        <BsClipboardHeartFill size="28px" color="#3895FF" />
        &nbsp; Save your favorite questions, answers, watch tags, and more
      </Subtext>
      <Subtext>
        <BsTrophy size="28px" color="#3895FF" />
        &nbsp; Earn reputation and badges
      </Subtext>
      <Endtext>
        <Grey>
          {' '}
          Collaborate and share knowledge with a private group for FREE.
        </Grey>
        <Blue>Get Stack Overflow for Teams free for up to 50 users.</Blue>
      </Endtext>
    </Wrapper>
  );
};

export default SignupText;
