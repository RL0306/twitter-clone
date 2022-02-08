import axios from "axios";
import React from "react";
import { IRegister } from "../interface/IRegister";

const Register = () => {
  
  const handleRegistration = async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    const registerRequest : IRegister = {username : formData.get("username") as string, email : formData.get("email") as string, password : formData.get("password") as string};

    const response = await axios.post("http://localhost:8080/api/register", JSON.stringify(registerRequest), {
      headers : {
        "content-type" : "application/json"
      }
    });
    
    const data = await response.data;
    console.log(data);
  }
  
  return (
    <form className="form-container column" onSubmit={(e) => handleRegistration(e)} >
      <div className="form-item center-element">
        <h1 className="center text">Register Form</h1>
      </div>

      <div className="form-item center-element">
        <input className="info" type="text" name="username" placeholder="username" required></input>
      </div>

      <div className="form-item center-element">
        <input className="info" type="text" name="email" placeholder="email" required></input>
      </div>

      <div className="form-item center-element">
        <input className="info" type="password" name="password" placeholder="password" required></input>
      </div>

      <div className="form-item center-element">
        <button type="submit">Create Account</button>
      </div>

    </form>
  )
}

export default Register;

