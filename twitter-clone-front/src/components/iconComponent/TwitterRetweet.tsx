import axios from "axios";

const TwitterRetweet = ({id, fetchTweets}: {id : number, fetchTweets ?: () => Promise<void>}) => {
  
  const createRetweet = async () => {
    const res = await axios.patch(`http://localhost:8080/api/tweet/retweet/${id}`,'',{
      withCredentials:true
    });

    fetchTweets && fetchTweets();
  }
  
  return (
    <span onClick={createRetweet}>
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
      <path d="M5 10v7h10.797l1.594 2h-14.391v-9h-3l4-5 4 5h-3zm14 4v-7h-10.797l-1.594-2h14.391v9h3l-4 5-4-5h3z"/>
      </svg>
    </span>
  )
}

export default TwitterRetweet;