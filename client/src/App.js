import Home from './pages/Home';
import Questions from './pages/Questions';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserEdit from './pages/UserEdit';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/questions" element={<Questions />} />
          <Route path="/useredit" element={<UserEdit />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
