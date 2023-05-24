import React, { useState } from "react";
import { v4 as uuidv4 } from "uuid";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ProductoNuevo.css";
import { fetchRegisterProductoNuevo } from "../../ServiceSoap/ProductoNuevo/RegisterProductoNuevo.js";

export default function ImageUploaderComponent() {
  const [images, setImages] = useState([]);
  const navigate = useNavigate();

  //Variables de registro mesas
  const [idTipo, setIdTipo] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [precio, setPrecio] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const imagen = images[0]?.url; // obtenemos la url de la imagen

      await fetchRegisterProductoNuevo(idTipo, descripcion, precio, imagen);
      alert("Producto registrado exitosamente!");
      navigate("/productos");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al registrar el producto");
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
        console.error("Error uploading image:", error);
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
                    <h3 className="mb-4">Registrar Productos! 🍴 </h3>
                    <p className="text-muted text-sm mb-5">
                      Registro de productos
                    </p>
                    <form onSubmit={handleSubmit}>
                      <div className="form-floating mb-3">
                        <input
                          className="form-control"
                          type="text"
                          value={idTipo}
                          onChange={(e) => setIdTipo(e.target.value)}
                          required
                        />
                        <label>Id Tipo producto</label>
                      </div>
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
                      <input
                        className="form-control"
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
