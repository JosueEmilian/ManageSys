import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { resetUser, logout } from "../Service/userAction";

function Home() {
  const dispatch = useDispatch();
  const email = useSelector((state) => state.email);

  const handleLogout = () => {
    console.log("Despachando logout...");
    dispatch(logout());
    console.log("Despachando resetUser...");
    dispatch(resetUser());
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
