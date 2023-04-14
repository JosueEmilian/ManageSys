import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import ReadPermisoSoap from "../../ServiceSoap/Permisos/ReadPermisoSoap.js";
import { SearchPermisoSoap } from "../../ServiceSoap/Permisos/SearchPermisoSoap.js";
import { DeletePermisoID } from "../../ServiceSoap/Permisos/DeletePermisoIDSoap.js";

function ReadPermisos() {
  const [permisos, setPermisos] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [showList, setShowList] = useState(true);
  const [originalPermisos, setOriginalPermisos] = useState([]);

  //UseEffect que carga los modulos utilizando ReadModuloSoap si la variable de estado "modulos" está vacía.
  useEffect(() => {
    const getPermisos = async () => {
      try {
        const permisos = await ReadPermisoSoap();
        setPermisos(permisos);
        setOriginalPermisos(permisos);
      } catch (error) {
        console.log(error);
      }
    };
    if (permisos.length === 0) {
      // Solo hace la solicitud si no hay permisos en la variable de estado
      getPermisos();
    }
  }, [permisos]);

  //Para funcion busqueda
  const handleSearch = async () => {
    try {
      let permisos;
      if (searchTerm === "") {
        permisos = originalPermisos;
      } else {
        permisos = await SearchPermisoSoap(searchTerm);
      }
      setPermisos(permisos);
      setShowList(true);
    } catch (error) {
      console.log(error);
    }
  };

  //funcion para eliminar Permiso
  async function handleDelete(id) {
    const response = await DeletePermisoID(id);
    if (response) {
      alert("Permiso eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">PERMISOS</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/dashboard/permiso/register"
          className="btn btn-warning text-white"
        >
          <FontAwesomeIcon
            icon={faUserPlus}
            style={{ color: "white", fontSize: "25px", margin: "0 10px" }}
          />
          ASIGNAR PERMISO
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
          <Form.Label>Buscar (ROL O MODULO)</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            value={searchTerm}
            onChange={(event) => setSearchTerm(event.target.value)}
            placeholder="Ingrese el Rol o Modulo"
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
              <th>ROL ASIGNADO</th>
              <th>MODULO ASIGNADO</th>
              <th className="text-right">Acciones</th>
            </tr>
          </thead>
          <tbody className="text-center">
            {permisos.map((permiso) => (
              <tr key={permiso.id}>
                <td>{permiso.id}</td>
                <td>
                  <Badge bg="warning" pill>
                    {permiso.nombreRol}
                  </Badge>
                </td>

                <td>
                  <p>{permiso.nombreModulo}</p>
                  <Badge bg="info">
                    <p>Path: {permiso.pathModulo}</p>
                  </Badge>
                </td>

                <td>
                  <button
                    type="button"
                    className="btn btn-outline-danger mx-3"
                    onClick={() => handleDelete(permiso.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </button>

                  <NavLink
                    to={`/dashboard/permiso/edit?id=${permiso.id}`}
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

export default ReadPermisos;
