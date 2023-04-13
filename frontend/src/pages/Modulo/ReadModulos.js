import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import ReadModulosSoap from "../../ServiceSoap/Modulo/ReadModuloSoap.js";
import { SearchModuloSoap } from "../../ServiceSoap/Modulo/SearchModuloSoap.js";
import { DeleteModuloIDSoap } from "../../ServiceSoap/Modulo/DeleteModuloIDSoap.js";

function ReadUsuarios() {
  const [modulos, setModulos] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [showList, setShowList] = useState(true);
  const [originalModulos, setOriginalModulos] = useState([]);

  //UseEffect que carga los modulos utilizando ReadModuloSoap si la variable de estado "modulos" está vacía.
  useEffect(() => {
    const getModulos = async () => {
      try {
        const modulos = await ReadModulosSoap();
        setModulos(modulos);
        setOriginalModulos(modulos);
      } catch (error) {
        console.log(error);
      }
    };
    if (modulos.length === 0) {
      // Solo hace la solicitud si no hay roles en la variable de estado
      getModulos();
    }
  }, [modulos]);

  //Para funcion busqueda
  const handleSearch = async () => {
    try {
      let modulos;
      if (searchTerm === "") {
        modulos = originalModulos;
      } else {
        modulos = await SearchModuloSoap(searchTerm);
      }
      setModulos(modulos);
      setShowList(true);
    } catch (error) {
      console.log(error);
    }
  };

  //funcion para eliminar Rol
  async function handleDelete(id) {
    const response = await DeleteModuloIDSoap(id);
    if (response) {
      alert("Modulo eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Modulos</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/dashboard/modulo/register"
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
          <Form.Label>Buscar (PATH O NOMBRE)</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            value={searchTerm}
            onChange={(event) => setSearchTerm(event.target.value)}
            placeholder="Ingrese el path o nombre"
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
              <th>PATH</th>
              <th>NIVEL</th>
              <th>ID_MODULO_PADRE</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody className="text-center">
            {modulos.map((modulo) => (
              <tr key={modulo.id}>
                <td>{modulo.id}</td>
                <td>{modulo.nombre}</td>
                <td>
                  <Badge bg="primary" pill>
                    {modulo.path}
                  </Badge>
                </td>
                <td>{modulo.nivel}</td>
                <td>{modulo.idModuloPadre}</td>

                <td>
                  <button
                    type="button"
                    className="btn btn-outline-danger mx-3"
                    onClick={() => handleDelete(modulo.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </button>

                  <NavLink
                    to={`/dashboard/modulo/edit?id=${modulo.id}`}
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
