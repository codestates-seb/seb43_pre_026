import Signup from './pages/Signup';
import Questions from './pages/Questions';
import { Routes, Route } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import Header from './components/Header';
import Home from './pages/Home';
import Login from './pages/Login';
import QuestionSubmit from './pages/QuestionSubmit';
import UserEdit from './pages/UserEdit';
import Users from './pages/Users';

const GlobalStyle = createGlobalStyle`
  html, body {
    margin: 0;
    padding: 0;
  }
`;

function App() {
  return (
    <>
      <GlobalStyle />
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/pages/login" element={<Login />} />
        <Route path="/questionsubmit" element={<QuestionSubmit />} />
        <Route path="/useredit" element={<UserEdit />} />
        <Route path="/users" element={<Users />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/questions" element={<Questions />} />
      </Routes>
    </>
  );
}

export default App;
