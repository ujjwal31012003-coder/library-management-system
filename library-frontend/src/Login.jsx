import { useState } from "react";
import axios from "axios";
import "./App.css";
import Dashboard from "./Dashboard";

function Login() {

const [loggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (event) => {

    event.preventDefault();

    try {

      const response = await axios.post(
        "http://localhost:8080/auth/login",
        {
          username: username,
          password: password
        }
      );

      console.log("Login response:", response.data);

      localStorage.setItem("token", response.data.token);

      setLoggedIn(true);

    } catch (error) {

      console.log("Login error:", error);

      alert("Invalid username or password");

    }
  };
if (loggedIn) {
  return <Dashboard />;
}
  return (
    <div className="login-container">

      <div className="login-box">

        <h1>📚 Library Management</h1>

        <h2>Librarian Login</h2>

        <form onSubmit={handleLogin}>

          <label>Username</label>

          <input
            type="text"
            placeholder="Enter username"
            value={username}
            onChange={(event) =>
              setUsername(event.target.value)
            }
          />

          <label>Password</label>

          <input
            type="password"
            placeholder="Enter password"
            value={password}
            onChange={(event) =>
              setPassword(event.target.value)
            }
          />

          <button type="submit">
            Login
          </button>

        </form>

      </div>

    </div>
  );
}

export default Login;