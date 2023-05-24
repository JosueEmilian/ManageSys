import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { fetchAreas } from "../../ServiceSoap/Area/ReadAreaSoap.js";
import { fetchDeleteArea } from "../../ServiceSoap/Area/DeleteAreaIDSoap.js";

function ReadAreas() {
  // CARGA DE AREAS
  const [areas, setAreas] = useState([]);
  useEffect(() => {
    const getAreas = async () => {
      const response = await fetchAreas();
      const areasResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarAreasResponse"]["return"];

      if (Array.isArray(areasResponse)) {
        const areasFormatted = areasResponse.map((area) => {
          return {
            id: area.id._text,
            descripcion: area.descripcion._text,
            estado: area.estado._text,
            estadoStr: area.strEstado._text,
          };
        });
        setAreas(areasFormatted);
      }
    };

    getAreas();
  }, []);

  //funcion para eliminar AREA
  async function handleDelete(id) {
    const response = await fetchDeleteArea(id);
    if (response) {
      alert("Area eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Registro de Areas</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/areas/areas-register"
          className="btn btn-warning text-white"
        >
          <FontAwesomeIcon
            icon={faUserPlus}
            style={{ color: "white", fontSize: "25px", margin: "0 10px" }}
          />
          Registrar
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
          <Form.Label>Buscar Area</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            placeholder="Ingrese el area"
          />
        </Form.Group>
        <button type="button" className="btn btn-warning mx-3">
          Buscar
        </button>
      </Form>
      <Table hover responsive className="table-principal">
        <thead className="text-center">
          <tr>
            <th>ID</th>
            <th>Descripcion</th>
            <th>Estado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {areas.map((area) => (
            <tr key={area.id}>
              <td>{area.id}</td>
              <td>
                <Badge bg="danger" pill>
                  {area.descripcion}
                </Badge>
              </td>
              <td>{area.estadoStr}</td>

              <td>
                <button
                  type="button"
                  className="btn btn-outline-danger mx-3"
                  onClick={() => handleDelete(area.id)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>

                <NavLink
                  to={`/areas/areas-edit?id=${area.id}`}
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
    </div>
  );
}

export default ReadAreas;
