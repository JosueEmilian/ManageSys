import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import ReadRoleSoap from "../../ServiceSoap/Rol/ReadRolSoap.js";
import { SearchRolSoap } from "../../ServiceSoap/Rol/SearchRolSoap.js";
import { DeleteRolID } from "../../ServiceSoap/Rol/DeleteRolIDSoap.js";
import "./ReadRoles.css";

function ReadUsuarios() {
  const [roles, setRoles] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [showList, setShowList] = useState(true);
  const [originalRoles, setOriginalRoles] = useState([]);

  //UseEffect que carga los roles utilizando ReadRoleSoap si la variable de estado "roles" está vacía.
  useEffect(() => {
    const getRoles = async () => {
      try {
        const roles = await ReadRoleSoap();
        setRoles(roles);
        setOriginalRoles(roles);
      } catch (error) {
        console.log(error);
      }
    };
    if (roles.length === 0) {
      // Solo hace la solicitud si no hay roles en la variable de estado
      getRoles();
    }
  }, [roles]);

  //Para funcion busqueda
  const handleSearch = async () => {
    try {
      let roles;
      if (searchTerm === "") {
        roles = originalRoles;
      } else {
        roles = await SearchRolSoap(searchTerm);
      }
      setRoles(roles);
      setShowList(true);
    } catch (error) {
      console.log(error);
    }
  };

  //funcion para eliminar Rol
  async function handleDelete(id) {
    const response = await DeleteRolID(id);
    if (response) {
      alert("Rol eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Roles</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/dashboard/rol/register"
          className="btn btn-warning text-white"
        >
          <FontAwesomeIcon
            icon={faUserPlus}
            style={{ color: "white", fontSize: "25px", margin: "0 10px" }}
          />
          Agregar
        </NavLink>
      </div>

      <Form className="d-flex mr-20">
        <Form.Group controlId="formBasicFilter" className="flex-grow-1">
          <Form.Label>Opciones:</Form.Label>
          <Form.Control className="w-auto" as="select">
            <option value="">Option</option>
          </Form.Control>
        </Form.Group>

        <Form.Group>
          <Form.Label>Buscar Rol (NOMBRE)</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            value={searchTerm}
            onChange={(event) => setSearchTerm(event.target.value)}
            placeholder="Ingrese el nombre"
            onKeyDown={(event) => {
              if (event.key === "Enter") {
                event.preventDefault();
                handleSearch();
              }
            }}
          />
        </Form.Group>
        <button
          type="button"
          className="btn btn-warning mx-3"
          onClick={handleSearch}
        >
          Buscar
        </button>
      </Form>
      {showList && (
        <Table hover responsive className="table-principal">
          <thead className="text-center">
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Descripcion</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody className="text-center">
            {roles.map((role) => (
              <tr key={role.id}>
                <td>{role.id}</td>
                <td>
                  {" "}
                  <Badge bg="success" pill>
                    {role.nombre}
                  </Badge>
                </td>
                <td>{role.descripcion}</td>
                <td>{role.estado}</td>

                <td>
                  <button
                    type="button"
                    className="btn btn-outline-danger mx-3"
                    onClick={() => handleDelete(role.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </button>

                  <NavLink
                    to={`/dashboard/rol/edit?id=${role.id}`}
                    className="btn btn-warning text-white"
                  >
                    <FontAwesomeIcon
                      style={{ color: "white" }}
                      icon={faPenToSquare}
                    />
                    Editar
                  </NavLink>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </div>
  );
}

export default ReadUsuarios;
