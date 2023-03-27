import React, { useState, useEffect } from "react";

function Usuarios() {
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/backend/api/usuarios")
      .then((response) => response.json())
      .then((data) => setUsuarios(data));
  }, []);

  return (
    <div>
      <h2>Usuarios</h2>
      <ul>
        {usuarios.map((usuario) => (
          <li key={usuario.id}>
            {usuario.nombre} {usuario.edad}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Usuarios;
