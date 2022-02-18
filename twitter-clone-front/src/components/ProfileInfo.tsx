import { useContext } from "react";
import { UserContext } from "./context/UserContext"
import "./ProfileInfo.css"

const ProfileInfo = () => {

  const userContext = useContext(UserContext);

  console.log(userContext?.user);

  return (
    <div className="profile-info-container">
      <div className="profile-flex-item">
        <div><img alt="go away error" src="https://via.placeholder.com/80x80"/></div>
        <h1>{userContext?.user?.username}</h1>
      </div>

      <div className="profile-stats-info">
        <div className="profile-info">
          <span>{userContext?.user?.tweets.length}</span>
          <p>TWEETS</p>
        </div>

        <div className="profile-info">
          <span>{userContext?.user?.following.length}</span>
          <p>FOLLOWING</p>
        </div>

        <div className="profile-info">
          <span>{userContext?.user?.follower.length}</span>
          <p>FOLLOWERS</p>
        </div>
      </div>

    </div>
  )
}

export default ProfileInfo;