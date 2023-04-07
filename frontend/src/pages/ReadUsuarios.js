import React from "react";
import { Badge, Button, Table } from "react-bootstrap";
import "./ReadUsuarios.css";

export default function ReadUsuario() {
  return (
    <div className="table-container">
      <Table hover responsive className="table-principal">
        <thead className="text-center">
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Usuario</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Estado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody className="text-center">
          <tr>
            <td>1</td>
            <td>
              <div className="d-flex align-items-center">
                <img
                  src="https://mdbootstrap.com/img/new/avatars/8.jpg"
                  alt=""
                  style={{ width: "45px", height: "45px" }}
                  className="rounded-circle"
                />
                <div className="ms-3">
                  <p className="fw-bold mb-1">Josue Emilian</p>
                </div>
              </div>
            </td>

            <td>Uyu Locon </td>
            <td>JosueEmilian1</td>
            <td>josueemilianroc@gmail.com</td>
            <td>
              <Badge bg="warning" pill>
                Administrador
              </Badge>
            </td>

            <td>Activo</td>

            <td>
              <Button variant="link" size="sm">
                Editar
              </Button>
              <Button variant="link" size="sm">
                Eliminar
              </Button>
            </td>
          </tr>
        </tbody>
      </Table>
    </div>
  );
}
