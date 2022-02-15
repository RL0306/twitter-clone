import "./Tweet.css";
import TwitterChat from "./iconComponent/TwitterChat";
import TwitterRetweet from "./iconComponent/TwitterRetweet";
import TwitterFavourite from "./iconComponent/TwitterFavourite";
import TwitterShare from "./iconComponent/TwitterShare";
import { UserContext } from "./context/UserContext";
import React, { useContext, useEffect } from "react";
import { ITweet, ITweetContainer } from "../interface/IAuthUser";

const Tweet : React.FC<ITweetContainer>= ({tweets} : ITweetContainer) => {

  const userContext = useContext(UserContext);

  console.log(tweets)

  return (

    <div className="tweet-container">
      {tweets && tweets.map((tweet) => 
      <div key={tweet.description} className="tweet-flex tweet-card tweet-column tweet-padding tweet-margin">
        <p>{tweet.username}</p>
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