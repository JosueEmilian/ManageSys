import React, { useEffect, useState } from "react";
import { Badge, Button, Card, ListGroup } from "react-bootstrap";
import { fetchPedidosPendientes } from "../../ServiceSoap/MonitorCocina/ReadPedidosPendientesSoap.js";
import { fetchUpdateEstadoPedido } from "../../ServiceSoap/MonitorCocina/UpdateEstadoPedidoSopa.js";
import "./MonitorCocina.css";

function MonitorCocina() {
  // CARGA DE PEDIDOS PENDIENTES
  const [pedidos, setPedidos] = useState([]);
  useEffect(() => {
    const getPedidos = async () => {
      const response = await fetchPedidosPendientes();
      const pedidosResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarPedidosPendientesResponse"][
          "return"
        ];

      const formatPedido = (pedido) => ({
        id: pedido.idPedido._text,
        area: pedido.area._text,
        mesa: pedido.mesa._text,
        cantidad: pedido.cantidad._text,
        descripcion: pedido.descripcion._text,
        imagen: pedido.imagen._text,
        observacion: pedido.observacion._text,
        hora: pedido.hora._text,
      });

      const pedidosFormatted = Array.isArray(pedidosResponse)
        ? pedidosResponse.map(formatPedido)
        : [formatPedido(pedidosResponse)];

      setPedidos(pedidosFormatted);
    };

    getPedidos();
  }, []);

  //funcion para despachar pedido
  async function handleDespachar(id) {
    const response = await fetchUpdateEstadoPedido(id);
    if (response) {
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <Card className="monitor-card">
      <Card.Header className="monitor-header">
        <h4>Monitor de Pedidos</h4>
      </Card.Header>
      <Card.Body>
        <ListGroup variant="flush">
          {pedidos.map((pedido) => (
            <ListGroup.Item
              key={pedido.id}
              className="order-item"
              style={{ transformStyle: "preserve-3d" }}
            >
              <div className="order-details">
                <div className="image-container">
                  <img
                    src={pedido.imagen}
                    alt="Producto"
                    className="img-producto"
                  />
                </div>
                <div className="order-info">
                  <h5>
                    {pedido.area}: {pedido.mesa}
                  </h5>
                  <p>Hora: {pedido.hora}</p>
                  <p>Observaciones: </p>
                  <p>{pedido.observacion}</p>
                </div>
                <Badge variant="warning" className="custom-badge">
                  Pendiente
                </Badge>
                <Button
                  variant="danger"
                  className="despachar-button"
                  onClick={() => handleDespachar(pedido.id)}
                >
                  Despachar
                </Button>
              </div>
              <hr />
              <ul>
                <li key={pedido.id}>
                  {pedido.cantidad} x {pedido.descripcion}
                </li>
              </ul>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </Card.Body>
    </Card>
  );
}

export default MonitorCocina;
