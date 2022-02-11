import Sidebar from "./Sidebar";
import "./Home.css"

const Home = () => {
  return (
    <div className="main-container">
      <Sidebar/>
      
      <div className="home-container">
        <h1>Hello</h1>
      </div>

    </div>
  )
}

export default Home;