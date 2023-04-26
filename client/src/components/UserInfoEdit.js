import React, { useState, useRef, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import profile from '../assets/profile.png';

const Container = styled.div`
  height: auto;
  width: 800px;
  margin: 0 auto;
  margin-bottom: 70px;
`;

const Title = styled.h1`
  padding-left: 20px;
  border-bottom: 1px solid #cacaca;
  padding-bottom: 40px;
`;

const Subhead = styled.div`
  padding-top: 20px;
  padding-left: 20px;
  font-size: 25px;
`;

const EditTitleInput = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid gray;
  margin-top: 20px;
  margin-bottom: 30px;
  padding: 20px;

  font-size: 18px;
  font-weight: bold;
`;

const Image = styled.img`
  width: 100px;
  height: 100px;
  border-radius: 5%;

  margin-right: 30px;
  margin-top: 15px;
`;

const InputContainer = styled.div`
  margin-top: 25px;
  > input {
    height: 30px;
    width: 750px;
    margin-top: 10px;
    border: 1px solid #cacaca;
  }
  > textarea {
    height: 100px;
    width: 750px;
    margin-top: 10px;
    border: 1px solid #cacaca;
  }
`;

const NameInput = styled(InputContainer)``;
const PasswordInput = styled(NameInput)``;
const TitleInput = styled(NameInput)``;
const AboutMe = styled(NameInput)``;

const OtherInfo = styled.div`
  padding-left: 20px;
  > .link {
    padding-left: 20px;
  }
`;

const Input = styled.input`
  width: 750px;
  height: 30px;
  margin-top: 10px;
  margin-bottom: 30px;
  border: 1px solid #cacaca;
`;

const Button = styled.div`
  display: flex;
  flex-direction: row;
  border: none;
  border-radius: 2px;
  margin-left: 10px;
  cursor: pointer;
`;
const Save = styled(Button)`
  height: 38px;
  width: 100px;
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
`;
const Cancel = styled(Button)`
  height: 38px;
  width: 100px;
  font-size: 15px;
  color: #0a95ff;
  border-radius: 3px;
  border: 1.2px solid #0a95ff;
  background-color: white;
  box-shadow: inset 0 1.2px 0 0 hsla(0, 0%, 100%, 0.4);
  flex-shrink: 0;

  justify-content: center;
  align-items: center;

  &:hover {
    background-color: aliceblue;
    border: 1.2px solid aliceblue;
  }
`;

const PasswordErrorMessage = styled.p`
  color: red;
  font-size: 12px;
  margin-top: 5px;
`;

const ImageUpload = styled.input`
  display: none;
`;

const UserInfoEdit = () => {
  const [memberNickname, setmemberNickname] = useState('');
  const [memberPassword, setmemberPassword] = useState('');
  const [title, setTitle] = useState('');
  const [memberDescription, setmemberDescription] = useState('');
  const [githubLink, setGithubLink] = useState('');
  const [memberName, setmemberName] = useState('');
  const [profileImage, setProfileImage] = useState(profile);
  const fileInputRef = useRef(null);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState('');

  const isPasswordValid = (password) => {
    const regex =
      /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
    return regex.test(password);
  };

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get('/members/1', {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        });
        const responseData = response?.data?.data; //모두 nullish인 경우 undefined를 반환
        if (responseData) {
          const {
            memberNickname,
            memberPassword,
            title,
            memberDescription,
            githubLink,
            memberName,
            profileImage,
          } = response.data.data;

          setmemberNickname(memberNickname || '');
          setmemberPassword(memberPassword || '');
          setTitle(title || '');
          setmemberDescription(memberDescription || '');
          setGithubLink(githubLink || '');
          setmemberName(memberName || '');
          setProfileImage(profileImage || profile);
        }
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      setProfileImage(reader.result);
    };
    //파일이 읽혀진 후 처리되는 콜백 함수 등록, 데이터 URL 형식으로 변경하여 변수에 저장

    if (file) {
      reader.readAsDataURL(file);
    } //
  };
  const handleSave = async () => {
    if (!isPasswordValid(memberPassword)) {
      setPasswordErrorMessage(
        '패스워드는 최소 8자 이상, 영문자와 숫자, 특수문자가 모두 포함되어야 합니다.'
      );
      return;
    }
    try {
      const data = {
        memberNickname,
        memberPassword,
        title,
        memberDescription,
        githubLink,
        memberName,
        profileImage,
      };
      console.log(data.memberNickname, data.profileImage);

      const response = await axios.put('/members/1', data, {
        headers: {
          'ngrok-skip-browser-warning': '69420',
        },
      });
      if (response.status === 200) {
        alert('Success');
        setTimeout(() => {
          window.location.href = '/Users'; // Users 페이지로 이동
        }, 1000);
        console.log(response.status);
      }
    } catch (error) {
      // 실패 시
      if (error.response) {
        alert('Error');
      }
    }
  };

  const handleCancel = () => {
    setTimeout(() => {
      window.location.href = '/Users'; // Users 페이지로 이동
    }, 1000);
    console.log('cancel');
  };

  return (
    <Container>
      <Title>Edit your profile</Title>
      <Subhead>Public information</Subhead>
      <EditTitleInput>
        <div>profile image</div>
        <Image
          src={profileImage}
          alt="profile"
          onClick={() => fileInputRef.current.click()}
        />
        <ImageUpload
          ref={fileInputRef}
          type="file"
          id="image-upload"
          accept="image/*"
          onChange={handleImageUpload}
        />
        <NameInput>
          <div>name</div>
          <input
            value={memberNickname}
            onChange={(e) => setmemberNickname(e.target.value)}
          />
        </NameInput>
        <PasswordInput>
          <div>password</div>
          <input
            value={memberPassword}
            onChange={(e) => setmemberPassword(e.target.value)}
          />
          {passwordErrorMessage && (
            <PasswordErrorMessage>{passwordErrorMessage}</PasswordErrorMessage>
          )}
        </PasswordInput>
        <TitleInput>
          <div>title</div>
          <input value={title} onChange={(e) => setTitle(e.target.value)} />
        </TitleInput>
        <AboutMe>
          <div>about me</div>
          <textarea
            value={memberDescription}
            onChange={(e) => setmemberDescription(e.target.value)}
          />
        </AboutMe>
      </EditTitleInput>
      <OtherInfo>
        <Subhead>Links</Subhead>
        <div className="link">GitHub link or username</div>
        <Input
          value={githubLink}
          onChange={(e) => setGithubLink(e.target.value)}
        />
        <Subhead>Private information</Subhead>
        <Input
          value={memberName}
          onChange={(e) => setmemberName(e.target.value)}
        />
      </OtherInfo>
      <Button>
        <Save onClick={handleSave}>Save profile</Save>
        <Cancel onClick={handleCancel}>Cancel</Cancel>
      </Button>
    </Container>
  );
};

export default UserInfoEdit;
