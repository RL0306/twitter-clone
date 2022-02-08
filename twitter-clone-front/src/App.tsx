import React, { useEffect } from 'react';
import axios from 'axios';
import Login from './components/Login';
import Register from './components/Register';


function App() {

  return (
    <div className="App">
     {/* <Login/> */}
     <Register/>
    </div>
  );
}

export default App;
