import { ReactComponentElement, ReactFragment } from "react"
import "./HomeHeader.css"
import Tweet from "./Tweet"
const HomeHeader = ({tweets} : {tweets:ReactFragment}) => {
  return (
  <section className="home-header">
    <h1 className="home-heading">Home</h1>
      <div><img alt="go away error" src="https://via.placeholder.com/40x40"/></div>
        <div className="home-flex home-margin-top home-width home-center">
          <img alt="go away error" src="https://via.placeholder.com/50x50"/>
          <div className="home-width"><form className="home-width"><input className="home-input home-width" type="text" placeholder="What's happening?"/></form></div>
        </div>

        <section className="home-bottom">
          <div className="home-flex home-images home-flex-end">
            <img alt="go away error" src="https://via.placeholder.com/30x30"/>
            <img alt="go away error" src="https://via.placeholder.com/30x30"/>
            <img alt="go away error" src="https://via.placeholder.com/30x30"/>
          </div>
          <a href="*">Tweet</a>
        </section>

      {tweets}

        
  </section>

  )
}

export default HomeHeader