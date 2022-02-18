import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Home from './components/pages/Home';
import { UserProvider } from './components/context/UserContext';
import Profile from './components/pages/Profile';


function App() {

  return (
    <div className="App">
      <UserProvider>
        <BrowserRouter>
        <Routes>
          {/* create authenticated route */}
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/profile" element={<Profile/>} />
        </Routes>
      </BrowserRouter>
     </UserProvider>
    </div>
  );
}

export default App;
