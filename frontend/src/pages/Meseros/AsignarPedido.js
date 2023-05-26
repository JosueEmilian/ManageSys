import React, { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import { useSelector } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import ModalCliente from "../../Components/ModalCliente.js";
import ModalClienteBusqueda from "../../Components/ModalClienteBusqueda.js";
import ModalProducto from "../../Components/ModalProducto.js";
import { fetchProductosNuevo } from "../../ServiceSoap/ProductoNuevo/ReadProductosNuevo.js";
import { fetchTransaction } from "../../ServiceSoap/Transaction/TransactionSoap.js";
import jsPDF from "jspdf";
import "jspdf-autotable";

function AsignarPedido() {
  const user = useSelector((state) => state.user);
  const navigate = useNavigate();

  // Recuperamos ID SELECCIONADO DE LA MESA
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  // PARA EL FORMULARIO
  const [cliente, setCliente] = useState("");
  const [observacion, setObservacion] = useState("");
  const [total, setTotal] = useState("");
  const [producto, setProducto] = useState("");
  const [productos, setProductos] = useState([]);
  const [cantidad, setCantidad] = useState("");
  const [precio, setPrecio] = useState("");
  const [selectedProduct, setSelectedProduct] = useState(null); // para precio

  // Calculamos el total al cambiar la cantidad
  const handleCantidadChange = (e) => {
    const newCantidad = e.target.value;
    const newTotal = newCantidad * precio;
    setCantidad(newCantidad);
    setTotal(newTotal);
  };

  // Calculamos el total al cambiar el precio
  const handlePrecioChange = (e) => {
    const newPrecio = e.target.value;
    const newTotal = cantidad * newPrecio;
    setPrecio(newPrecio);
    setTotal(newTotal);
  };

  // Trabajamos el MODAL para ingreso Cliente, busqueda cliente, busqueda producto
  const [modalClienteShow, setModalClienteShow] = useState(false);
  const [modalClienteSearchShow, setModalClienteSearchShow] = useState(false);
  const [modalProductoShow, setModalProductoShow] = useState(false);

  const openModal = () => {
    setModalClienteShow(true);
  };

  const closeModal = () => {
    setModalClienteShow(false);
  };

  const openModalSearch = () => {
    setModalClienteSearchShow(true);
  };

  const closeModalSearch = () => {
    setModalClienteSearchShow(false);
  };

  const openModalProducto = () => {
    setModalProductoShow(true);
  };

  const closeModalProducto = () => {
    setModalProductoShow(false);
  };

  const handleModalSelect = (id) => {
    const selected = productos.find((producto) => producto.id === id);
    setSelectedProduct(selected);
    setProducto(id);
    setPrecio(selected.precio);
    closeModalProducto();
  };

  const handleFormSubmit = (formData) => {
    setCliente(formData);
    closeModal();
    closeModalSearch();
  };

  // Fetch para realizar el pedido
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await fetchTransaction(
        cliente.nombre,
        cliente.nit,
        cliente.razon_social,
        cliente.nickname,
        cliente.direccion,
        cliente.telefono,
        id,
        user.id,
        observacion,
        producto,
        cantidad,
        precio,
        total,
        observacion
      );
      alert("Pedido realizado exitosamente!");
      handleDownloadPDF();
      navigate("/monitoreo-pedido");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al realizar el pedido");
    }
  };

  //CARGA DE PRODUCTOS
  useEffect(() => {
    const getProductos = async () => {
      const response = await fetchProductosNuevo();
      const productosResponse =
        response["S:Envelope"]["S:Body"]["ns2:getProductosResponse"]["return"];

      const formatProducto = (producto) => ({
        id: producto.idCombo._text,
        descripcion: producto.descripcion._text,
        imagen: producto.imagen._text,
        precio: producto.precio._text,
      });

      const productosFormatted = Array.isArray(productosResponse)
        ? productosResponse.map(formatProducto)
        : [formatProducto(productosResponse)];

      setProductos(productosFormatted);
    };

    getProductos();
  }, []);

  // Descargar PDF
  const handleDownloadPDF = () => {
    const doc = new jsPDF();

    doc.setFont("helvetica", "normal");
    doc.setFontSize(12);
    doc.setTextColor("#000000"); // Color del texto

    doc.setFont("helvetica", "bold");
    doc.setFontSize(18);
    doc.text("ManageSys Restaurant", 10, 20);

    // Información del cliente
    doc.setFont("helvetica", "normal");
    doc.setFontSize(12);
    doc.text(`Cliente: ${cliente.nombre}`, 10, 40);
    doc.text(`Nit: ${cliente.nit}`, 10, 50);
    doc.text(`Razón Social: ${cliente.razon_social}`, 10, 60);
    doc.text(`Nickname: ${cliente.nickname}`, 10, 70);

    // Información del pedido
    doc.text(`Mesa Numero: ${id}`, 10, 110);
    doc.text(`Mesero Codigo: ${user.id}`, 10, 120);
    doc.text(`Observación: ${observacion}`, 10, 130);
    doc.text(`Producto: ${producto}`, 10, 140);
    doc.text(`Cantidad: ${cantidad}`, 10, 150);
    doc.text(`Precio: ${precio}`, 10, 160);
    doc.text(`Total: ${total}`, 10, 170);

    doc.save("ManageSysRestaurant.pdf");
  };

  return (
    <div className="row align-items-center justify-content-center">
      <div className="col-lg-6 px-lg-4">
        <div className="card">
          <div className="card-header px-lg-5">
            <div className="card-heading text-center">
              Sistema de pedidos - Restaurant ManageSys
            </div>
          </div>
          <div className="card-body p-lg-5">
            <h3 className="mb-4">Cerrar la Orden! 📑</h3>
            <p className="text-muted text-sm mb-5">Sistema de pedidos</p>
            <div className="form-floating mb-3">
              <Button variant="danger" onClick={openModal} type="button">
                Asignar Cliente
              </Button>
              <Button
                variant="danger"
                onClick={openModalSearch}
                type="button"
                className="ms-3"
              >
                Asignar Cliente Existente
              </Button>

              <Modal show={modalClienteShow} onHide={closeModal}>
                <Modal.Header closeButton>
                  <Modal.Title>Datos del cliente</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                  <ModalCliente onSubmit={handleFormSubmit} />
                </Modal.Body>
                <Modal.Footer>
                  <Button variant="secondary" onClick={closeModal}>
                    Cerrar
                  </Button>
                </Modal.Footer>
              </Modal>

              <Modal show={modalClienteSearchShow} onHide={closeModalSearch}>
                <Modal.Header closeButton>
                  <Modal.Title>Datos buscar</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                  <ModalClienteBusqueda onSubmit={handleFormSubmit} />
                </Modal.Body>
                <Modal.Footer>
                  <Button variant="secondary" onClick={closeModalSearch}>
                    Cerrar
                  </Button>
                </Modal.Footer>
              </Modal>

              <ModalProducto
                show={modalProductoShow}
                onHide={() => setModalProductoShow(false)}
                data={productos}
                onSelect={handleModalSelect}
              />
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-floating mb-3">
                <textarea
                  className="form-control"
                  value={observacion}
                  onChange={(e) => setObservacion(e.target.value)}
                  required
                />
                <label>Observación</label>
              </div>

              <div className="form-floating mb-3">
                <textarea
                  className="form-control"
                  value={total}
                  onChange={(e) => setTotal(e.target.value)}
                  required
                  disabled
                />
                <label>Total</label>
              </div>

              <div className="mt-3 align-item-center justify-content-center form-group row">
                <button className="btn btn-danger" type="submit">
                  Cerrar la Orden
                </button>
              </div>
            </form>
          </div>
          <div className="card-footer px-lg-5 py-lg-4">
            <div className="text-center text-muted">
              Sistema de pedidos - Restaurant ManageSys
            </div>
          </div>
        </div>
      </div>
      <div className="col-lg-6 px-lg-4 ">
        {/* Segundo formulario */}
        <div className="card">
          <div className="card-header px-lg-5">
            <div className="card-heading text-center">
              Sistema de pedidos - Restaurant ManageSys
            </div>
          </div>
          <div className="card-body p-lg-5">
            <h3 className="mb-4 text-center">Detalle Pedido! 🍔🍕</h3>

            <p className="text-muted text-sm mb-5">Sistema de pedidos</p>
            {/* <form onSubmit={handleSubmit}> */}
            <div className="form-floating mb-3">
              <Button
                variant="danger"
                onClick={openModalProducto}
                type="button"
                className="ms-3"
              >
                Asignar Producto
              </Button>
            </div>

            <div className="form-floating mb-3">
              <input
                className="form-control"
                type="text"
                value={cantidad}
                onChange={handleCantidadChange}
                required
              />
              <label>Cantidad</label>
            </div>

            <div className="form-floating mb-3">
              <input
                className="form-control"
                type="text"
                value={precio}
                readOnly
                required
              />

              <label>Precio</label>
            </div>

            <div className="form-floating mb-3">
              <textarea
                className="form-control"
                value={observacion}
                onChange={(e) => setObservacion(e.target.value)}
                required
              />
              <label>Observación</label>
            </div>

            <div className="form-floating mb-3">
              <input
                className="form-control"
                type="text"
                value={total}
                onChange={(e) => setTotal(e.target.value)}
                required
                disabled
              />
              <label>Total Linea</label>
            </div>
            {/* </form> */}
          </div>

          <div className="card-footer px-lg-5 py-lg-4">
            <div className="text-center text-muted">
              Sistema de pedidos - Restaurant ManageSys
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AsignarPedido;
