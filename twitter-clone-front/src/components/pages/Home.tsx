import Sidebar from "../Sidebar";
import "../HomeHeader.css"
import "../Home.css"
import axios from "axios";
import {useLocation} from 'react-router-dom';
import { useEffect, useContext } from "react";
import { UserContext } from "../context/UserContext";
import HomeHeader from "../HomeHeader";
import Tweet from "../Tweet";


const Home = () => {
  
  const userContext = useContext(UserContext);

  const {state} : any = useLocation();
  const username = state.username;

  useEffect(() => {
    const fetchData = async () => {
      console.log("fetching")
      const res =  await axios.get(`http://localhost:8080/api/user/username/${username}`, {withCredentials : true});
      userContext?.setUser(await res.data);
    }
    fetchData();

  },[])




//get user from jsession id

  return (
    <div className="home-main-container">
      <Sidebar/>
      
      {/* Really didn't want to pass it but have it individually here was messing up styles */}
      <HomeHeader tweets={<Tweet/>}/>

    </div>
  )
}

export default Home;