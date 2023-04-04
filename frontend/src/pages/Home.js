import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { resetUser, logout } from "../Service/userAction";

function Home() {
  const dispatch = useDispatch();
  const email = useSelector((state) => state.email);

  const handleLogout = () => {
    localStorage.removeItem("user"); // borra el usuario del almacenamiento local
    dispatch(logout()); // llama a la acción de logout
    dispatch(resetUser()); // llama a la acción de resetUser
  };

  return (
    <div>
      <h1>Home</h1>
      <p>Holaaa {email}</p>
      <button onClick={handleLogout}>Cerrar sesión</button>
    </div>
  );
}

export default Home;
