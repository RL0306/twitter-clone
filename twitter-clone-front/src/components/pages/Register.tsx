import axios from "axios";
import React, { useState } from "react"
import { IRegister } from "../../interface/IRegister";
import "../helper/Form.css"

const Register = () => {

  const [response, setResponse] = useState(undefined);
  
  const handleRegistration = async (e : React.BaseSyntheticEvent) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    const registerRequest : IRegister = {username : formData.get("username"), email : formData.get("email"), password : formData.get("password")} as IRegister;

    try{
      const response = await axios.post("http://localhost:8080/api/register", JSON.stringify(registerRequest), {
      headers : {
        "content-type" : "application/json"
      }
    }) 
      const {authentication} = await response.data;
      setResponse(authentication)
    
    } catch(error) {
      
      const {authentication} = await error.error.data;
      setResponse(authentication)
    }
  }
  
  return (
    <main className="info-container info-center">
    <form className="info-form-container info-column" onSubmit={(e) => handleRegistration(e)}>
      <h1 className="info-form-title">Register Form</h1>
      <input className="info-input-info" type="text" name="username"placeholder="username"required></input>
      <input className="info-input-info" type="text"name="email"placeholder="email"required></input>
      <input className="info-input-info" type="password"name="password"placeholder="password"required></input>
      <button className="info-input-info" type="submit">Log In</button>
      <p className="info-text-center info-response-info">{response}</p>
    </form>
  </main>
  );
}

export default Register;

