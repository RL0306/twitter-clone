import axios from "axios";
import React from "react"
import { ILogin } from "../interface/ILogin";
import "./Login.css"

const Login = () => {

  const handleLogin= async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const loginRequest : ILogin = {username : formData.get("username") as string, password : formData.get("password") as string};

    const response = await axios.post("http://localhost:8080/api/login", JSON.stringify(loginRequest));
    const data = await response.data;

    console.log(data);

  }


  return (
    <form className="form-container column" onSubmit={(e) => handleLogin(e)} >
      <div className="form-item center-element">
        <h1 className="center text">Login Form</h1>
      </div>

      <div className="form-item center-element">
        <input className="info" type="text" name="username" placeholder="username" required></input>
      </div>

      <div className="form-item center-element">
        <input className="info" type="password" name="password" placeholder="password" required></input>
      </div>

      <div className="form-item center-element">
        <button type="submit">Log In</button>
      </div>

    </form>
  )
}

export default Login;