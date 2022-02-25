import Sidebar from "../Sidebar";
import "../Profile.css"
import ProfileInfo from "../ProfileInfo";
import Tweet from "../Tweet";
import { UserContext } from "../context/UserContext";
import { useContext, useEffect, useState } from "react";
import { ITweetContainer } from "../../interface/IAuthUser";
import axios from "axios";

const Profile = () => {

  const userContext = useContext(UserContext);
  const [tweets, setTweets] = useState<ITweetContainer["tweets"]>([]);

  const fetchTweets = async () => {
    //need to handle seperate query that orders it by time
    
    const res = await axios.get(`http://localhost:8080/api/user/${userContext?.user?.id}`, {withCredentials : true});
    const data = await res.data;
    setTweets(data.tweets);
  }

  useEffect(() => {
    fetchTweets();
  },[])

  return (
    <div className="profile-main-container">
      <Sidebar/>

      <div className="profile-main">
        <ProfileInfo/>
        <Tweet tweets={tweets} fetchTweets = {fetchTweets}/>
      </div>
    </div>
  )
}

export default Profile;