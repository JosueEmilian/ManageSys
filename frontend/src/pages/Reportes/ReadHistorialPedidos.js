import React, { useState, useEffect } from "react";
import { Badge, Form, Table } from "react-bootstrap";
import { fetchHistorialPedidos } from "../../ServiceSoap/Reportes/HistorialPedidosSoap.js";

function ReadHistorialProductos() {
  // CARGA DE PRODUCTOS
  const [historialPedidos, setHistorialPedidos] = useState([]);
  useEffect(() => {
    const getHistorialPedido = async () => {
      const response = await fetchHistorialPedidos();
      const historialPedidoResponse =
        response["S:Envelope"]["S:Body"]["ns2:historialPedidosResponse"][
          "return"
        ];

      const formatProducto = (historialPedido) => ({
        id: historialPedido.idPedido._text,
        mesa: historialPedido.mesa._text,
        empleado: historialPedido.empleado._text,
        cliente: historialPedido.cliente._text,
        estado: historialPedido.estadoPedido._text,
        fecha: historialPedido.fechaPedido._text,
        observacion: historialPedido.observacionPedido._text,
        total: historialPedido.total._text,
      });

      const historialPedidoFormatted = Array.isArray(historialPedidoResponse)
        ? historialPedidoResponse.map(formatProducto)
        : [formatProducto(historialPedidoResponse)];

      setHistorialPedidos(historialPedidoFormatted);
    };

    getHistorialPedido();
  }, []);

  return (
    <div className="table-principal ">
      <h1 className="text-center">Historial de Pedidos</h1>

      <Form className="d-flex mr-20">
        <Form.Group controlId="formBasicFilter" className="flex-grow-1">
          <Form.Label>Opciones:</Form.Label>
          <Form.Control className="w-auto" as="select">
            <option value="">Option</option>
          </Form.Control>
        </Form.Group>
      </Form>
      <Table hover responsive className="table-principal">
        <thead className="text-center">
          <tr>
            <th>ID</th>
            <th>Empleado</th>
            <th>Cliente</th>
            <th>Mesa</th>
            <th>Observacion</th>
            <th>Total</th>
            <th>Fecha Pedido</th>
            <th>Estado</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {historialPedidos.map((historialPedido) => (
            <tr key={historialPedido.id}>
              <td>{historialPedido.id}</td>
              <td>{historialPedido.empleado}</td>
              <td>{historialPedido.cliente}</td>
              <td>{historialPedido.mesa}</td>
              <td>{historialPedido.observacion}</td>
              <td>{historialPedido.total}</td>
              <td>{historialPedido.fecha}</td>
              <td>
                {" "}
                <Badge bg="danger" pill>
                  {historialPedido.estado}
                </Badge>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default ReadHistorialProductos;
