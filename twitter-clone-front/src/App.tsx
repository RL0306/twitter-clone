import { BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Home from './components/pages/Home';
import { UserProvider } from './components/context/UserContext';
import Profile from './components/pages/Profile';
import ProtectedRoutes from './components/ProtectedRoutes';


function App() {

  return (
    <div className="App">
      <UserProvider>
        <BrowserRouter>
        <Routes>
          {/* create authenticated route */}
          <Route path="/login" element={<Login/>} />

          <Route element={<ProtectedRoutes/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/register" element={<Register/>} />
          <Route path="/profile" element={<Profile/>} />
          </Route>
        </Routes>
      </BrowserRouter>
     </UserProvider>
    </div>
  );
}

export default App;
