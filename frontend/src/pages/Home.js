import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { resetUser, logout } from "../Service/userAction";

function Home() {
  const dispatch = useDispatch();
  const email = useSelector((state) => state.email);
  const isAdmin = useSelector((state) => state.isAdmin);

  const handleLogout = () => {
    localStorage.removeItem("user"); // borra el usuario del almacenamiento local
    dispatch(logout()); // llama a la acción de logout
    dispatch(resetUser()); // llama a la acción de resetUser
    console.log("isAdmin: ", isAdmin);
  };

  return (
    <div>
      <h1>Home</h1>
      <p>Holaaa {email}</p>
      {isAdmin ? "Admin" : "Not admin"}
      {isAdmin ? (
        <p>El usuario es un administrador</p>
      ) : (
        <p>El usuario no es un administrador</p>
      )}

      <button onClick={handleLogout}>Cerrar sesión</button>
    </div>
  );
}

export default Home;
