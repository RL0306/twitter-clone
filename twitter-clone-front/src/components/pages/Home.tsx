import Sidebar from "../Sidebar";
import "../HomeHeader.css"
import "../Home.css"
import HomeHeader from "../HomeHeader";
import Tweet from "../Tweet";
import axios from "axios";


const Home = () => {

(async () => {
  const res = await axios.get("http://localhost:8080/api/user/3", {withCredentials: true});
  const data = await res.data;
  console.log(data)
})()

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