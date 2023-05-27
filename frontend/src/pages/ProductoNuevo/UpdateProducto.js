import React, { useEffect, useState } from "react";
import { v4 as uuidv4 } from "uuid";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import "./ProductoNuevo.css";
import { fetchProductoID } from "../../ServiceSoap/ProductoNuevo/GetProductoIDSoap.js";
import { fetchUpdateProducto } from "../../ServiceSoap/ProductoNuevo/UpdateProductoSoap.js";

export default function ActualizarProductos() {
  const [images, setImages] = useState([]);
  const navigate = useNavigate();

  //Variables de registro productos
  const [idTipo, setIdTipo] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [precio, setPrecio] = useState("");
  const [estado, setEstado] = useState("");

  // Recuperamos ID SELECCIONADO y mostramos sus datos en los inputs
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  // CARGA DE DATA PRODUCTO
  const [productosData, setProductosData] = useState({});
  const mapeoDatos = {
    idTipo: setIdTipo,
    descripcion: setDescripcion,
    precio: setPrecio,
    estado: setEstado,
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchProductoID(id);
        const productosResponse =
          response["S:Envelope"]["S:Body"]["ns2:getInfoProductoResponse"][
            "return"
          ];
        setProductosData(productosResponse);

        Object.entries(mapeoDatos).forEach(([field, setter]) => {
          if (productosResponse[field]?._text) {
            setter(productosResponse[field]._text);
          }
        });
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [id]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const imagen = images[0]?.url; // obtenemos la url de la imagen

      await fetchUpdateProducto(
        id,
        idTipo,
        descripcion,
        precio,
        estado,
        imagen
      );
      alert("Producto actualizado exitosamente!");
      navigate("/productos");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al actualizar el producto");
    }
  };

  //Funcion para subir imagenes a cloudinary
  function handleUpload(event) {
    const file = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", "dhxsahx9");

    axios
      .post("https://api.cloudinary.com/v1_1/dsyj0wwnn/image/upload", formData)
      .then((response) => {
        const { secure_url, public_id } = response.data;
        setImages((prevImages) => [
          ...prevImages,
          { url: secure_url, public_id: public_id },
        ]);
      })
      .catch((error) => {
        console.error("Error subir img:", error);
      });
  }

  return (
    <div>
      <div className="App">
        <div className=" align-items-center py-4 bg-gray-100 vh-100">
          <div className="container">
            <div className="row align-items-center justify-content-center">
              <div className="show col-lg-6 px-lg-4">
                <div className="card">
                  <div className="card-header px-lg-5">
                    <div className="card-heading text-center">
                      Sistema de pedidos - Restaurant ManageSys
                    </div>
                  </div>
                  <div className="card-body p-lg-5">
                    <h3 className="mb-4">Actualizacion de Productos! 🍴 </h3>
                    <p className="text-muted text-sm mb-5">
                      Actualizacion de productos
                    </p>
                    <form onSubmit={handleSubmit}>
                      <div className="form-floating mb-3">
                        <input
                          className="form-control"
                          type="text"
                          value={descripcion}
                          onChange={(e) => setDescripcion(e.target.value)}
                          required
                        />
                        <label>Descripcion</label>
                      </div>

                      <div className="form-floating mb-3">
                        <input
                          className="form-control"
                          type="text"
                          value={precio}
                          onChange={(e) => setPrecio(e.target.value)}
                          required
                        />
                        <label>Precio</label>
                      </div>
                      <div className="form-group row mt-3 d-flex justify-content-center">
                        <div className="col-sm-4 d-flex flex-column align-items-center">
                          <label htmlFor="rol" className="mb-1 text-center">
                            TIPO PRODUCTO:
                          </label>

                          <select
                            id="rol"
                            className="form-control text-center"
                            value={idTipo}
                            onChange={(e) => setIdTipo(e.target.value)}
                          >
                            <option value="">Define el tipo</option>
                            <option value="1">Alimentos</option>
                            <option value="2">Bebidas</option>
                            <option value="3">Postres</option>
                            <option value="4">Otros</option>
                          </select>
                        </div>

                        <div className="col-sm-4 d-flex flex-column align-items-center">
                          <label htmlFor="estado" className="mb-1 text-center">
                            ESTADO
                          </label>
                          <select
                            id="estado"
                            className="form-control text-center"
                            value={estado}
                            onChange={(event) => setEstado(event.target.value)}
                          >
                            <option value="">Define el Estado</option>
                            <option value="1">Activo</option>
                            <option value="0">Inactivo</option>
                          </select>
                        </div>
                      </div>
                      <input
                        className="form-control mt-3"
                        type="file"
                        accept="image/*"
                        onChange={handleUpload}
                      />

                      <div>
                        {images.map((image) => (
                          <img
                            className="img-producto"
                            key={uuidv4()}
                            src={image.url}
                            alt="Uploaded"
                            style={{ marginTop: "10px" }}
                          />
                        ))}
                      </div>
                      <div className="mt-3 align-item-center justify-content-center form-group row">
                        <button className="btn btn-warning " type="submit">
                          Ingresar
                        </button>
                      </div>
                    </form>
                  </div>
                  <div className="card-footer px-lg-5 py-lg-4 ">
                    <div className="text-center text-muted">
                      Sistema de pedidos - Restaurant ManageSys
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
