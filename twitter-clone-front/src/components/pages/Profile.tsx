import Sidebar from "../Sidebar";
import "../Profile.css"
import ProfileInfo from "../ProfileInfo";
import Tweet from "../Tweet";
import { UserContext } from "../context/UserContext";
import { useContext } from "react";
import { ITweetContainer } from "../../interface/IAuthUser";

const Profile = () => {

  const userContext = useContext(UserContext);



  return (
    <div className="profile-main-container">
        <Sidebar/>

        <div className="profile-container">
          <ProfileInfo/>
          {/* <Tweet tweets={userContext?.user?.tweets}/> */}
        </div>
    </div>
  )
}

export default Profile;