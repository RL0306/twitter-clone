import "./Sidebar.css"
import TwitterLogo from "./iconComponent/TwitterLogo"; 
import NavButton from "./helper/NavButton";

const Sidebar = () => {
  return (
    <div className="sidebar-main-container sidebar-padding">
      <TwitterLogo/>

      <nav className="sidebar-navigation sidebar-column">
        <NavButton to="/" text="Home" image={<TwitterLogo/>}/>
        <NavButton to="" text="Explore" image={<TwitterLogo/>}/>
        <NavButton to="" text="Notifications" image={<TwitterLogo/>}/>
        <NavButton to="" text="Messages" image={<TwitterLogo/>}/>
        <NavButton to="" text="Bookmarks" image={<TwitterLogo/>}/>
        <NavButton to="" text="Lists" image={<TwitterLogo/>}/>
        <NavButton to="/profile" text="Profile" image={<TwitterLogo/>}/>
        <NavButton to="" text="More" image={<TwitterLogo/>}/>
        <a className="sidebar-tweet sidebar-padding" href="/">Tweet</a>
      </nav>
    </div>
  )
}

export default Sidebar;