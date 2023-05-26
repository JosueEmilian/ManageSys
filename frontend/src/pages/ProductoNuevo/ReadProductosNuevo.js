import React, { useState, useEffect } from "react";
import { Button, Card } from "react-bootstrap";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { fetchProductosNuevo } from "../../ServiceSoap/ProductoNuevo/ReadProductosNuevo.js";
import "./ProductoNuevo.css";
import { fetchDeleteProducto } from "../../ServiceSoap/ProductoNuevo/DeleteProductoIDSoap.js";

function ReadProductosNuevos() {
  // CARGA DE PRODUCTOS
  const [productos, setProductos] = useState([]);
  useEffect(() => {
    const getProductos = async () => {
      const response = await fetchProductosNuevo();
      const productosResponse =
        response["S:Envelope"]["S:Body"]["ns2:getProductosResponse"]["return"];

      const formatProducto = (producto) => ({
        id: producto.idCombo._text,
        descripcion: producto.descripcion._text,
        imgUrl: producto.imagen._text,
        precio: producto.precio._text,
      });

      const productosFormatted = Array.isArray(productosResponse)
        ? productosResponse.map(formatProducto)
        : [formatProducto(productosResponse)];

      setProductos(productosFormatted);
    };

    getProductos();
  }, []);

  //funcion para eliminar Producto(Cambio de estado)
  async function handleDelete(id) {
    const response = await fetchDeleteProducto(id);
    if (response) {
      alert("Producto eliminada correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }
  return (
    <div className="card-container modal-card-container">
      <h1 className="text-center">Productos disponibles</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/productos/productos-register"
          className="btn btn-warning text-white"
        >
          <FontAwesomeIcon icon={faUserPlus} className="mr-2" />
          Registrar
        </NavLink>
      </div>

      <div className="filter-container mb-4">
        <div className="form-group ml-3">
          <input
            type="search"
            className="form-control"
            style={{ width: "200px" }}
            placeholder="Ingrese el producto"
          />
        </div>
        <div className="form-group">
          <Button variant="warning" className="form-control">
            Buscar
          </Button>
        </div>
      </div>

      <div className="row card-row modal-card-row">
        {productos.map((producto) => (
          <div key={producto.id} className="col-lg-4 col-md-6 mb-4">
            <Card className="product-card">
              <Card.Img
                variant="top"
                src={producto.imgUrl}
                className="card-image"
              />
              <Card.Body>
                <Card.Title>{producto.descripcion}</Card.Title>
                <Card.Text>
                  Precio: <span className="price-badge">{producto.precio}</span>
                </Card.Text>
                <div className="button-container">
                  <Button
                    variant="outline-danger"
                    className="mr-3"
                    onClick={() => handleDelete(producto.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </Button>
                  <NavLink
                    to={`/productos/productos-edit?id=${producto.id}`}
                    className="btn btn-warning text-white"
                  >
                    <FontAwesomeIcon icon={faPenToSquare} className="mr-2" />
                    Editar
                  </NavLink>
                </div>
              </Card.Body>
            </Card>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ReadProductosNuevos;
