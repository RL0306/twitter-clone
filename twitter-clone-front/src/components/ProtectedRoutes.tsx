import { useContext, useEffect} from "react"
import { Outlet } from "react-router-dom";
import { UserContext } from "./context/UserContext"
import Login from "./pages/Login";


const useAuth = () => {
  const userContext = useContext(UserContext);
  return userContext && userContext.user;
}

const ProtectedRoutes = () => {
  const isAuth = useAuth();
  console.log(isAuth)
  return isAuth !== null ? <Outlet /> : <Login/>
}

export default ProtectedRoutes