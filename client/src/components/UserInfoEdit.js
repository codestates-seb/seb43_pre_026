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
  const [memberNickname, setMemberNickname] = useState('');
  const [memberPassword, setMemberPassword] = useState('');
  const [title, setTitle] = useState('');
  const [memberDescription, setMemberDescription] = useState('');
  const [githubLink, setGithubLink] = useState('');
  const [memberName, setMemberName] = useState('');
  const [profileImage, setProfileImage] = useState(profile);
  const fileInputRef = useRef(null);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState('');
  let memberId;
  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    const tokenData = JSON.parse(accessToken);
    memberId = tokenData.memberId;
    axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
  }

  //패스워드 유효성 검사, 최소 8자 이상, 영문자와 숫자, 특수문자가 모두 포함
  const isPasswordValid = (password) => {
    const regex =
      /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
    return regex.test(password);
  };

  useEffect(() => {
    //useEffect를 사용하여 컴포넌트가 마운트될 때 서버에서 사용자 정보를 가져오는 작업
    const getData = async () => {
      try {
        const response = await axios.get(
          `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`
        );
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

          setMemberNickname(memberNickname || '');
          setMemberPassword(memberPassword || '');
          setTitle(title || '');
          setMemberDescription(memberDescription || '');
          setGithubLink(githubLink || '');
          setMemberName(memberName || '');
          setProfileImage(profileImage || profile); //서버에서 받은 이미지가 없을 시 기본 이미지 적용
        }
      } catch (error) {
        console.error(error);
      }
    };
    getData();
  }, []);

  const handleImageUpload = (e) => {
    //사용자가 이미지를 업로드하면 이미지를 읽어 profileImage 상태 변수에 저장하는 역할
    const file = e.target.files[0]; //이벤트 객체에서 업로드된 파일 목록 가져와서 file 변수에 저장
    const reader = new FileReader(); //객체를 생성하고 파일 읽고, 파일을 비동기적으로 읽는데 사용

    reader.onloadend = () => {
      //성공적으로 읽힌 후 실행될 콜백 함수를 등록
      setProfileImage(reader.result); //콜백함수에서 reader.result를 사용하여 읽는 데이터를 가져와 setProfileImage를 호출하여 상태 변수 profileImage에 저장, 이미지가 프로필 이미지가 표시되고 사용자 인터페이스가 업데이트
    };

    if (file) {
      reader.readAsDataURL(file); //파일이 존재하는 경우에만 호출, URL 형식으로 읽음
    }
  };

  const handleSave = async () => {
    //수정된 사용자 정보를 서버에 저장, 유효성 검사 수행 -> 유효하지 않으면 에러메시지/ 수정된 사용자 정보를 서버에 PUT 요청
    if (!isPasswordValid(memberPassword)) {
      setPasswordErrorMessage(
        '패스워드는 최소 8자 이상, 영문자와 숫자, 특수문자가 모두 포함되어야 합니다.'
      );
      return;
    }
    try {
      const formData = new FormData(); //서버에 전송할 데이터 생성, append 메소드를 사용하여 formData에 전송할 데이터 추가, 각각의 필드에 대해 키와 값을 전달
      formData.append('profileImage', profileImage);
      formData.append('memberNickname', memberNickname);
      formData.append('memberPassword', memberPassword);
      formData.append('title', title);
      formData.append('memberDescription', memberDescription);
      formData.append('githubLink', githubLink);
      formData.append('memberName', memberName);

      const response = await axios.put(
        `http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`,
        formData
      );
      if (response.status === 200) {
        alert('Success');
        setTimeout(() => {
          window.location.href = '/Users'; // Users 페이지로 이동
        }, 1000);
      }
    } catch (error) {
      // 실패 시
      if (error.response) {
        alert('Error');
      }
    }
  };

  const handleCancel = () => {
    //cancle 버튼 누를 시 정보 수정을 취소하고 이전 페이지로 이동
    setTimeout(() => {
      window.location.href = '/Users'; // Users 페이지로 이동
    }, 1000);
  };

  return (
    <Container>
      <Title>Edit your profile</Title>
      <Subhead>Public information</Subhead>
      <EditTitleInput>
        <div>profile image</div>
        <Image
          src={profileImage} //서버 URL
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
            onChange={(e) => setMemberNickname(e.target.value)}
          />
        </NameInput>
        <PasswordInput>
          <div>password</div>
          <input
            value={memberPassword}
            onChange={(e) => setMemberPassword(e.target.value)}
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
            onChange={(e) => setMemberDescription(e.target.value)}
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
          onChange={(e) => setMemberName(e.target.value)}
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
