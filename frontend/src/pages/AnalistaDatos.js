import React from "react";
import { useSelector } from "react-redux";

function AnalistaDatos() {
  const user = useSelector((state) => state.user);

  return (
    <div>
      <h1>HOLA ANALISTA {user.usuario}</h1>
    </div>
  );
}

export default AnalistaDatos;
