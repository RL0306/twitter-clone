import Sidebar from "../Sidebar";
import "../HomeHeader.css"
import "../Home.css"
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import HomeHeader from "../HomeHeader";
import Tweet from "../Tweet";
import { ITweetContainer } from "../../interface/IAuthUser";
import { UserContext } from "../context/UserContext";


const Home = () => {
  
  const [tweets, setTweets] = useState<ITweetContainer["tweets"]>([]);

  const userContext = useContext(UserContext);

  
  const fetchTweets = async() => {
    const res = await axios.get("http://localhost:8080/api/tweet/all", {withCredentials : true});
    const data = await res.data;
    setTweets(data);
  }

  useEffect(() => {
    fetchTweets();
  },[])


  return (
    <div className="home-main-container">
      <Sidebar/>
      <div className="home-container">
        <HomeHeader/>
        <Tweet tweets={tweets} fetchTweets={fetchTweets}/>
      </div>
    </div>
  )
}

export default Home;