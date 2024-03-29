import React from "react";

export interface IAuthUser {
  username : string | null,
  id : number,
  tweets : ITweet[],
  follower : IFollower[],
  following : IFollowing[],
}

export interface ITweet {
  id : number,
  description : string,
  retweets : number,
  favourites : number,
  username : string
}

export interface ITweetContainer {
  tweets : ITweet[] | undefined
  fetchTweets : () => Promise<void>;
}

export interface IFollower {
  username : string
}

export interface IFollowing {
  username : string;
}

export type IUserContextType = {
  user : IAuthUser | null,
  setUser : React.Dispatch<React.SetStateAction<IAuthUser | null>>
}







