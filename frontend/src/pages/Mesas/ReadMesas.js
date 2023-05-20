import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { fetchMesas } from "../../ServiceSoap/Mesa/ReadMesasSoap.js";
import { fetchDeleteMesa } from "../../ServiceSoap/Mesa/DeleteMesaIDSoap.js";

function ReadMesas() {
  // CARGA DE MESAS
  const [mesas, setMesas] = useState([]);
  useEffect(() => {
    const getMesas = async () => {
      const response = await fetchMesas();
      const mesasResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarMesasResponse"]["return"];
      if (Array.isArray(mesasResponse)) {
        const mesasFormatted = mesasResponse.map((mesa) => {
          return {
            id: mesa.id._text,
            descripcionArea: mesa.descArea._text,
            descripcion: mesa.descripcion._text,
            asientos: mesa.asientos._text,
            descripcionEstado: mesa.strEstadoMesa._text,
          };
        });
        setMesas(mesasFormatted);
      }
    };

    getMesas();
  }, []);

  //funcion para eliminar AREA
  async function handleDelete(id) {
    const response = await fetchDeleteMesa(id);
    if (response) {
      alert("Mesa eliminada correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Registro de Mesas</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/mesas/mesas-register"
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
          <Form.Label>Buscar Mesa</Form.Label>
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
            <th>Area</th>
            <th>Descripcion</th>
            <th>Asientos</th>
            <th>Estado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {mesas.map((mesa) => (
            <tr key={mesa.id}>
              <td>{mesa.id}</td>
              <td>{mesa.descripcionArea}</td>
              <td>
                <Badge bg="danger" pill>
                  {mesa.descripcion}
                </Badge>
              </td>
              <td>{mesa.asientos}</td>
              <td>{mesa.descripcionEstado}</td>

              <td>
                <button
                  type="button"
                  className="btn btn-outline-danger mx-3"
                  onClick={() => handleDelete(mesa.id)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>

                <NavLink
                  to={`/mesas/mesas-edit?id=${mesa.id}`}
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

export default ReadMesas;
