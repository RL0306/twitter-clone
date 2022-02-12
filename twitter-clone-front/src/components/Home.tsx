import Sidebar from "./Sidebar";
import "./Home.css"
import HomeHeader from "./HomeHeader";

const Home = () => {
  return (
    <div className="home-main-container">
      <Sidebar/>
      <HomeHeader/>

    </div>
  )
}

export default Home;