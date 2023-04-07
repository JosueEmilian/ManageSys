import React from "react";
import { useSelector } from "react-redux";

function Dashboard() {
  const user = useSelector((state) => state.user);

  return (
    <div>
      <h1>DASHBOARD</h1>
      {/* verifica primero si no es nulo */}
      {<p>Holaaa {user?.usuario}</p>}
      <a href="/dashboard/rol">Rol</a> <br></br>
      <a href="/dashboard/modulo">Modulo</a>
      <br></br>
      <a href="/dashboard/user">Usuarios</a> <br></br>
      <a href="/dashboard/permiso">Permisos</a>
    </div>
  );
}

export default Dashboard;
