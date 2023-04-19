import Signup from "./pages/Signup";
import Questions from "./pages/Questions";
import { Routes, Route } from "react-router-dom";
import { createGlobalStyle } from "styled-components";
import Header from "./components/Header";

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
        <Route path="/" element={<Signup />} />
        <Route path="/q" element={<Questions />} />
      </Routes>
    </>
  );
}

export default App;
