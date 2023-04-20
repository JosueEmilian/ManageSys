// import React from "react";
import { useSelector } from "react-redux";

function Home() {
  const user = useSelector((state) => state.user);

  return (
    <div>
      <h1>Home</h1>
      {/* verifica primero si no es nulo */}
      {user && <p>Hola {user.usuario}</p>}
      {user && <p>tu rol es {user.rol}</p>}
      {user && <p>modulo es {user.modulo}</p>}
      {user && <p>tu path es {user.ruta}</p>}
    </div>
  );
}

export default Home;
