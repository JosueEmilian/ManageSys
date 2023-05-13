import React from "react";
import { useSelector } from "react-redux";

function AsignarPedido() {
  const user = useSelector((state) => state.user);

  return (
    <div className="text-center">
      <h1>Mesero Asignado: {user.usuario}</h1>
      <h1>Asignar Pedido</h1>
    </div>
  );
}

export default AsignarPedido;
