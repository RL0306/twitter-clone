import { NavLink } from "react-router-dom"
import { INav } from "../../interface/INav";
import TwitterHome from "../iconComponent/TwitterHome";

const NavButton = ({to, text, image} : INav ) => {
  return (
    <NavLink to={to} className="sidebar-current">
      {image}<span className="sidebar-nav-text">{text}</span>
    </NavLink>
  )
}

export default NavButton;