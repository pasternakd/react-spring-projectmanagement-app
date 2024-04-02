import React, { useState } from "react";              
import { useLocalState } from "../../util/useLocalStorage";
import { Link } from "react-router-dom";

// styles
import './Signup.css'

const Signup = () => {
 const [inputs, setInputs] = useState({})

 
 const [jwt, setJwt] = useLocalState("", "jwt")

 function handleChange(e) 
 {
   const name = e.target.name;
   const value = e.target.value;
   setInputs(values => ({...values, [name]: value}));
 }

 const sendSignUpRequest = () => {
      const reqBody = {
        username: inputs.username,
        password: inputs.password,
      }
    
      fetch("api/auth/signup", {
        headers: {
          "Content-Type": "application/json",
        },
         method : "post",
         body: JSON.stringify(reqBody)
      }).then((response) => {
        if (response.status === 200) {
          alert("Registration successful! You can now log in.");
          window.location.href = "login"
        } 
      }).catch(message => {
        alert(message);
      });
 }

 return (
  <>
  <div className="auth-form"> 
     <h2>Register</h2>
      <label>
        <span>username:</span>
        <input
          required
          type="email" 
          name="username"
          onChange={handleChange} 
        />
      </label>
      <label>
        <span>password:</span>
        <input 
          required
          type="password" 
          name="password"
          onChange={handleChange} 
        />
      </label>
    <div>
        <button id="submit" type="button" className="btn" onClick={() => sendSignUpRequest()}>signup</button>
    </div>
</div>
<Link to="/login" className="redirect">Already have an account?</Link>
</>
)
}


export default Signup;