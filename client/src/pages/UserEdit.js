import React from 'react';
import UserInfo from '../components/UserInfo';
import UserInfoEdit from '../components/UserInfoEdit';
import Header from '../components/Header';

const UserEdit = () => {
  return (
    <div>
      <Header />
      <UserInfo />
      <UserInfoEdit />
    </div>
  );
};

export default UserEdit;
