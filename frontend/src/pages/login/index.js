import React, { useState } from "react";              
import { useLocalState } from "../../util/useLocalStorage";
import { Link } from "react-router-dom";

// styles
import './Login.css'

const Login = () => {
 const [inputs, setInputs] = useState({})

 
 const [jwt, setJwt] = useLocalState("", "jwt")

 function handleChange(e) 
 {
   const name = e.target.name;
   const value = e.target.value;
   setInputs(values => ({...values, [name]: value}));
 }

 const sendLoginRequest = () => {
      if (!inputs.username || !inputs.password) {
        alert("Username and password are required.");
        return;
      }

      const reqBody = {
        username: inputs.username,
        password: inputs.password,
      }
    
      fetch("api/auth/login", {
        headers: {
          "Content-Type": "application/json",
        },
         method : "post",
         body: JSON.stringify(reqBody)
      }).then((response) => {
        if (response.status === 200) {
          return Promise.all([response.json(), response.headers])
        } else 
          return Promise.reject("Invalid login attempt")
      })
        .then(([body,headers]) => {
          setJwt(headers.get("authorization"));
          window.location.href = "login"
        })
        .catch(message => {
          alert(message);
        });

 }

 return (
  <>
  <div className="auth-form"> 
     <h2>login</h2>
      <label>
        <span>username:</span>
        <input
          type="email" 
          name="username"
          onChange={handleChange} 
          required
        />
      </label>
      <label>
        <span>password:</span>
        <input 
          type="password" 
          name="password"
          onChange={handleChange} 
          required
        />
      </label>
    <div>
        <button id="submit" type="button" className="btn" onClick={() => sendLoginRequest()}>Login</button>
    </div>
</div>
<Link to="/signup" className="redirect">Create an account</Link>
</>
)
}


export default Login;