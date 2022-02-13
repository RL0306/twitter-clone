import "./Tweet.css";
import TwitterChat from "./iconComponent/TwitterChat";
import TwitterRetweet from "./iconComponent/TwitterRetweet";
import TwitterFavourite from "./iconComponent/TwitterFavourite";
import TwitterShare from "./iconComponent/TwitterShare";

const Tweet = () => {
  return (
    <div className="tweet-flex tweet-card tweet-column tweet-padding tweet-margin">
      <p>Brie</p>
      <p>Giving standup comedy a go. Open mic starts at 7, hit me up if you want ticket.</p>

      <div className="tweet-flex tweet-logos tweet-position tweet-margin">
        <TwitterChat/>
        <TwitterRetweet/>
        <TwitterFavourite/>
        <TwitterShare/>
      </div>

    </div>
  )
}

export default Tweet;