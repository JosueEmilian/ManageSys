import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { login, setIsAdmin } from "../Service/userAction";
import { loginUsr } from "../ServiceSoap/LoginSoap";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  function handleSubmit(event) {
    event.preventDefault();
    loginUsr(
      email,
      password,
      (email, isAdminValue) => {
        dispatch(login(email, isAdminValue));
        dispatch(setIsAdmin(isAdminValue));
        navigate("/home");
      },
      (errorMessage) => {
        console.log("Error al iniciar sesión:", errorMessage);
      }
    );
  }

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <br />
        <button className="btn btn-warning" type="submit">
          Submit
        </button>
      </form>
    </div>
  );
}

export default Login;
