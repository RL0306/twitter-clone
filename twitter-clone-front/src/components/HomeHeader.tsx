import axios from "axios"
import React, { ReactComponentElement, ReactFragment } from "react"
import {ITweetRequest } from "../interface/ITweet"
import "./HomeHeader.css"
import Tweet from "./Tweet"
const HomeHeader = () => {

  const createTweet = async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const description : ITweetRequest = {description : formData.get("description") as string}

    const res = await axios.post("http://localhost:8080/api/tweet", JSON.stringify(description), {
      withCredentials : true,
      headers : {
        "content-type" : "application/json",
      }
      
    });    

    e.target.reset();

  }



  return (
  <section className="home-header">
    <h1 className="home-heading">Home</h1>
    <div>
      <img alt="go away error" src="https://via.placeholder.com/40x40"/>
    </div>
      
    <div className="home-flex home-margin-top home-width home-center">
      <img className="home-first" alt="go away error" src="https://via.placeholder.com/50x50"></img>
      <div className="home-width">
        <form onSubmit={(e) => createTweet(e)} className="home-width">
          <input className="home-input home-width" name="description" type="text" placeholder="What's happening?"/>
      

      <section className="home-bottom">
        <div className="home-flex home-images home-flex-end">
          <img alt="go away error" src="https://via.placeholder.com/30x30"/>
          <img alt="go away error" src="https://via.placeholder.com/30x30"/>
          <img alt="go away error" src="https://via.placeholder.com/30x30"/>
        </div>
        <button className="home-btn" type="submit">Tweet</button>
      </section>
        </form>
      </div>
    </div>
    

        
  </section>

  )
}

export default HomeHeader