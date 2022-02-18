import "./Tweet.css";
import TwitterChat from "./iconComponent/TwitterChat";
import TwitterRetweet from "./iconComponent/TwitterRetweet";
import TwitterFavourite from "./iconComponent/TwitterFavourite";
import TwitterShare from "./iconComponent/TwitterShare";
import React from "react";
import { ITweetContainer } from "../interface/IAuthUser";

const Tweet : React.FC<ITweetContainer>= ({tweets, fetchTweets} : ITweetContainer ) => {

  return (

    <div className="tweet-container">
      {tweets && tweets.map((tweet) => 
      <div key={tweet.description} className="tweet-flex tweet-card tweet-column tweet-padding tweet-margin">
        <p>{tweet.username}</p>
        <p>{tweet.description}</p>

        <div className="tweet-flex tweet-logos tweet-position tweet-margin">
          <TwitterChat/>
          <div className="tweet-flex tweet-center tweet-gap">
            <TwitterRetweet id={tweet.id} fetchTweets ={fetchTweets}/>
            <span>{tweet.retweets}</span>
          </div>
          <div className="tweet-flex tweet-center tweet-gap">
            <TwitterFavourite id={tweet.id} fetchTweets={fetchTweets} />
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