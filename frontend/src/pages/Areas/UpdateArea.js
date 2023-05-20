import React, { useEffect, useState } from "react";
import "../Login.css";
import { fetchAreaID } from "../../ServiceSoap/Area/GetAreaIDSoap.js";
import { fetchUpdateArea } from "../../ServiceSoap/Area/UpdateAreaSoap.js";
import { useLocation, useNavigate } from "react-router-dom";

function UpdateArea() {
  const navigate = useNavigate();

  // Variables de registro usuario
  const [estado, setEstado] = useState("");
  const [descripcion, setDescripcion] = useState("");

  // Recuperamos ID SELECCIONADO y mostramos sus datos en los inputs
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  // PARA CARGA  DATA DE AREA EN EL FORMULARIO
  const [areaData, setAreaData] = useState({});
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchAreaID(id);
        const areasResponse =
          response["S:Envelope"]["S:Body"]["ns2:obtenerAreaPorIDResponse"][
            "return"
          ];
        setAreaData(areasResponse); // Almacena los datos en el estado local
        if (areasResponse.descripcion?._text) {
          setDescripcion(areasResponse.descripcion._text); // Sincroniza el valor de descripción
        }
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [id]);

  //para Actualizar el area
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await fetchUpdateArea(descripcion, estado, id);
      alert("Area actualizada exitosamente!");
      navigate("/areas");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al actualizar el area.");
    }
  };
  return (
    <div className="App">
      <div className="align-items-center py-4 bg-gray-100 vh-100">
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
                  <h3 className="mb-4">Actualizacion Areas! 🧑‍💻 </h3>
                  <p className="text-muted text-sm mb-5">
                    Actualizacion de areas
                  </p>

                  <form onSubmit={handleSubmit}>
                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={descripcion}
                        onChange={(event) => setDescripcion(event.target.value)}
                        required
                      />
                      <label>Descripcion</label>
                    </div>

                    <div className="form-group row mt-3 d-flex justify-content-center">
                      <div className="col-sm-4 d-flex flex-column align-items-center">
                        <label className="mb-1 text-center">ESTADO</label>
                        <select
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

                    <div className="mt-3 align-item-center justify-content-center form-group row">
                      <button className="btn btn-warning" type="submit">
                        Actualizar
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
  );
}

export default UpdateArea;
