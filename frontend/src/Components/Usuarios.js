import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";

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
      <Button variant="danger">Primary</Button>
    </div>
  );
}

export default Usuarios;
