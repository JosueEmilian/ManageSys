import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { fetchClientes } from "../../ServiceSoap/Cliente/ReadClienteSoap.js";
import { fetchDeleteCliente } from "../../ServiceSoap/Cliente/DeleteClienteIDSoap.js";

function ReadClientes() {
  // CARGA DE CLIENTES
  const [clientes, setClientes] = useState([]);
  useEffect(() => {
    const getClientes = async () => {
      const response = await fetchClientes();
      const clientesResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarClientesResponse"][
          "return"
        ];

      const formatCliente = (cliente) => ({
        id: cliente.idCliente._text,
        nombre: cliente.nombre._text,
        nickname: cliente.nickname._text,
        nit: cliente.nit._text,
        razonSocial: cliente.razonSocial._text,
        telefono: cliente.telefono._text,
      });

      const clientesFormatted = Array.isArray(clientesResponse)
        ? clientesResponse.map(formatCliente)
        : [formatCliente(clientesResponse)];

      setClientes(clientesFormatted);
    };

    getClientes();
  }, []);

  //funcion para eliminar Cliente
  async function handleDelete(id) {
    const response = await fetchDeleteCliente(id);
    if (response) {
      alert("Cliente eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Clientes</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/clientes/clientes-register"
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
          <Form.Label>Buscar cliente</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            placeholder="Ingrese el cliente"
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
            <th>Nombre</th>
            <th>Nickname</th>
            <th>Nit</th>
            <th>Razon Social</th>
            <th>Telefono</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {clientes.map((cliente) => (
            <tr key={cliente.id}>
              <td>{cliente.id}</td>
              <td>{cliente.nombre}</td>
              <td>{cliente.nickname}</td>
              <td>
                {" "}
                <Badge bg="danger" pill>
                  {cliente.nit}
                </Badge>
              </td>
              <td>{cliente.razonSocial}</td>
              <td>{cliente.telefono}</td>

              <td>
                <button
                  type="button"
                  className="btn btn-outline-danger mx-3"
                  onClick={() => handleDelete(cliente.id)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>

                <NavLink
                  to={`/clientes/clientes-edit?id=${cliente.id}`}
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

export default ReadClientes;
