import Sidebar from "../Sidebar";
import "../HomeHeader.css"
import "../Home.css"
import axios from "axios";
import {useLocation} from 'react-router-dom';
import { useEffect, useContext, useState } from "react";
import { UserContext } from "../context/UserContext";
import HomeHeader from "../HomeHeader";
import Tweet from "../Tweet";
import { ITweet, ITweetContainer } from "../../interface/IAuthUser";


const Home = () => {
  
  const userContext = useContext(UserContext);

  const {state} : any = useLocation();
  const username = state.username;

  const [tweets, setTweets] = useState<ITweetContainer["tweets"]>([]);
  

  useEffect(() => {
    const fetchUser = async () => {
      const res =  await axios.get(`http://localhost:8080/api/user/username/${username}`, {withCredentials : true});
      userContext?.setUser(await res.data);
    }
    fetchUser();

    const fetchTweets = async() => {
      const res = await axios.get("http://localhost:8080/api/tweet/all", {withCredentials : true});
      const data = await res.data;
      setTweets(data);
    }

    fetchTweets();

  },[])




//get user from jsession id

  return (
    <div className="home-main-container">
      <Sidebar/>
      
      {/* Really didn't want to pass it but have it individually here was messing up styles */}
      <HomeHeader tweets={<Tweet tweets={tweets}/>}/>

    </div>
  )
}

export default Home;