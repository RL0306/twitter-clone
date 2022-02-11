import "./Sidebar.css"
import TwitterLogo from "./iconComponent/TwitterLogo";
import TwitterHome from "./iconComponent/TwitterHome";

const Sidebar = () => {
  return (
    <div className="sidebar-main-container">
      <TwitterLogo/>

      <nav className="sidebar-navigation column">
        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Home</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Explore</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Notifications</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Messages</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Bookmarks</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Lists</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">Profiles</a>
          </h3>
        </div>

        <div className="sidebar-item">
          <TwitterHome/>
          <h3 className="sidebar-label">
            <a href="*">More</a>
          </h3>
        </div>

        {/* //why tf does this make label 100% width but if u do a first a isn't */}
        <label className="sidebar-tweet center">
          <a href="*">Tweet</a>
        </label>
      </nav>
    </div>
  )
}

export default Sidebar;