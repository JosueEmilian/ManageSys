import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { fetchProductosNuevo } from "../../ServiceSoap/ProductoNuevo/ReadProductosNuevo.js";
import "./ProductoNuevo.css";
import { fetchDeleteMesa } from "../../ServiceSoap/Mesa/DeleteMesaIDSoap.js";

function ReadProductosNuevos() {
  // CARGA DE PRODUCTOS
  const [productos, setProductos] = useState([]);
  useEffect(() => {
    const getProductos = async () => {
      const response = await fetchProductosNuevo();
      const productosResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarProductosNuevoResponse"][
          "return"
        ];

      const formatProducto = (producto) => ({
        id: producto.id._text,
        descripcion: producto.descripcion._text,
        imgUrl: producto.img._text,
        precio: producto.precio._text,
      });

      const productosFormatted = Array.isArray(productosResponse)
        ? productosResponse.map(formatProducto)
        : [formatProducto(productosResponse)];

      setProductos(productosFormatted);
    };

    getProductos();
  }, []);

  return (
    <div className="table-principal ">
      <h1 className="text-center">Registro de Mesas</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/productos/productos-register"
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
            <th>Descripcion</th>
            <th>Imagen</th>
            <th>Precio</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {productos.map((producto) => (
            <tr key={producto.id}>
              <td>{producto.id}</td>
              <td>{producto.descripcion}</td>
              <td>
                <img
                  src={producto.imgUrl}
                  alt="Producto"
                  className="img-producto"
                />
              </td>

              <td>
                <Badge bg="danger" pill>
                  {producto.precio}
                </Badge>
              </td>

              <td>
                <button
                  type="button"
                  className="btn btn-outline-danger mx-3"
                  //   onClick={() => handleDelete(producto.id)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>

                <NavLink
                  to={`/mesas/mesas-edit?id=${producto.id}`}
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

export default ReadProductosNuevos;
