import "./Tweet.css";
import TwitterChat from "./iconComponent/TwitterChat";
import TwitterRetweet from "./iconComponent/TwitterRetweet";
import TwitterFavourite from "./iconComponent/TwitterFavourite";
import TwitterShare from "./iconComponent/TwitterShare";
import { UserContext } from "./context/UserContext";
import { useContext } from "react";

const Tweet = () => {

  const userContext = useContext(UserContext);

  const tweets = userContext?.user?.tweets;


  return (

    
    <div className="tweet-container">
      {tweets && tweets.map((tweet) => 
      <div className="tweet-flex tweet-card tweet-column tweet-padding tweet-margin">
        <p>{userContext.user?.username}</p>
        <p>{tweet.description}</p>

        <div className="tweet-flex tweet-logos tweet-position tweet-margin">
          <TwitterChat/>
          <div className="tweet-flex tweet-center tweet-gap">
            <TwitterRetweet/>
            <span>{tweet.retweets}</span>
          </div>
          <div className="tweet-flex tweet-center tweet-gap">
            <TwitterFavourite/>
            <span>{tweet.favourites}</span>
          </div>
          <TwitterShare/>
        </div>

      </div>
      )}
    </div>
  )
}

export default Tweet;