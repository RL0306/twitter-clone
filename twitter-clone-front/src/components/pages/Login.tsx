import axios from "axios";
import React, { useState, useContext } from "react"
import { useNavigate } from "react-router-dom";
import { ILogin } from "../../interface/ILogin";
import { UserContext } from "../context/UserContext";


import "../helper/Form.css"


const Login = () => {
  const navigate = useNavigate();
  const [response, setResponse] = useState(undefined);
  const userContext = useContext(UserContext);


  

  const handleLogin= async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const loginRequest : ILogin = {username : formData.get("username"), password : formData.get("password")} as ILogin;

  try{
    const response = await axios.post("http://localhost:8080/api/login", JSON.stringify(loginRequest),  {
      headers : {
        "content-type" : "application/json",
      },

      withCredentials : true
    })

    const {authentication} = await response.data;
    setResponse(authentication);


    //set auth here maybe
    const res =  await axios.get(`http://localhost:8080/api/user/username/${loginRequest.username}`, {withCredentials : true});
    userContext?.setUser(await res.data);
    
    //redirect user to home page
    setTimeout(() => {
      navigate("/")
    }, 1000)

  } catch(error){
    const {authentication} = await error.response.data;
    setResponse(authentication);
  }
    
  }
  return (
    <main className="info-container info-center">
    <form className="info-form-container info-column" onSubmit={(e) => handleLogin(e)}>
      <h1 className="info-text-center">Login Form</h1>
      <input className="info-input-info" type="text" name="username"placeholder="username"required></input>
      <input className="info-input-info" type="password"name="password"placeholder="password"required></input>
      <button className="info-input-info" type="submit">Log In</button>
      <p className="info-text-center info-response-info">{response}</p>
    </form>
  </main>
  );
}

export default Login;