import { useState } from "react";
import { Button, Modal, Table } from "react-bootstrap";
import "../pages/ProductoNuevo/ProductoNuevo.css";

function ModalProducto(props) {
  const [selectedId, setSelectedId] = useState(null);

  const handleRowClick = (id) => {
    setSelectedId(id);
  };

  return (
    <Modal show={props.show} onHide={props.onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Seleccione el Producto</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Table striped hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Descripcion</th>
              <th>Precio</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {props.data.map((row) => (
              <tr
                key={row.id}
                onClick={() => handleRowClick(row.id)}
                className={selectedId === row.id ? "table-primary" : ""}
              >
                <td>{row.id}</td>
                <td>{row.descripcion}</td>
                <td>{row.precio}</td>
                <td>
                  <img className="img-producto" src={row.imagen} alt="" />
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.onHide}>
          Cerrar
        </Button>
        <Button
          variant="warning"
          onClick={() => props.onSelect(selectedId)}
          disabled={!selectedId}
        >
          Seleccionar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ModalProducto;
