// import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { resetUser, logout } from "../Service/userAction";

function Home() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);

  const handleLogout = () => {
    localStorage.removeItem("user"); // borra el usuario del almacenamiento local
    dispatch(logout()); // llama a la acción de logout
    dispatch(resetUser()); // llama a la acción de resetUser
  };

  return (
    <div>
      <h1>Home</h1>
      {/* verifica primero si no es nulo */}
      {user && <p>Holaaa {user.usuario}</p>}
      {user && <p>tu rol es {user.rol}</p>}
      {user && <p>modulo es {user.modulo}</p>}
      {user && <p>tu path es {user.ruta}</p>}

      <button onClick={handleLogout}>Cerrar sesión</button>
    </div>
  );
}

export default Home;
