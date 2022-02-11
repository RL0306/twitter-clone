import axios from "axios";
import React, { useState } from "react"
import { ILogin } from "../interface/ILogin";
import "./Form.css"

const Login = () => {

  const [response, setResponse] = useState(undefined);

  const handleLogin= async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const loginRequest : ILogin = {username : formData.get("username"), password : formData.get("password")} as ILogin;

  try{
    const response = await axios.post("http://localhost:8080/api/login", JSON.stringify(loginRequest), {
      headers : {
        "content-type" : "application/json"
      }
    })

    const {authentication} = await response.data;
    setResponse(authentication);

  } catch(error){
    const {authentication} = await error.response.data;
    setResponse(authentication);
  }
    
  }
  return (
    <main className="info-container center">
    <form className="info-form-container column" onSubmit={(e) => handleLogin(e)}>
      <h1 className="text-center">Login Form</h1>
      <input className="input-info" type="text" name="username"placeholder="username"required></input>
      <input className="input-info" type="password"name="password"placeholder="password"required></input>
      <button className="input-info" type="submit">Log In</button>
      <p className="text-center response-info">{response}</p>
    </form>
  </main>
  );
}

export default Login;