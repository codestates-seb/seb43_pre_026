import Signup from './pages/Signup';
import Questions from './pages/Questions';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Home from './pages/Home';
import Login from './pages/Login';
import QuestionSubmit from './pages/QuestionSubmit';
import UserEdit from './pages/UserEdit';
import Users from './pages/Users';
import Search from './pages/Search';

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/questionsubmit" element={<QuestionSubmit />} />
        <Route path="/useredit" element={<UserEdit />} />
        <Route path="/users" element={<Users />} />
        <Route path="/boards/:boardId" element={<Questions />} />
        <Route path="/search/:searchValue" element={<Search />} />
      </Routes>
    </>
  );
}

export default App;
