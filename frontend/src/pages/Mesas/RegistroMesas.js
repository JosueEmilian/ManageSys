import React, { useEffect, useState } from "react";
import "../Login.css";
import { fetchAreas } from "../../ServiceSoap/Area/ReadAreaSoap.js";
import { fetchRegisterMesa } from "../../ServiceSoap/Mesa/RegisterMesaSoap.js";
import { useNavigate } from "react-router-dom";

function RegistroMesas() {
  const navigate = useNavigate();

  //Variables de registro mesas
  const [idArea, setIdArea] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [asientos, setAsientos] = useState("");
  const [estado, setEstado] = useState("");

  // CARGA DE AREAS
  const [areas, setAreas] = useState([]);
  useEffect(() => {
    const getAreas = async () => {
      const response = await fetchAreas();
      const areasResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarAreasResponse"]["return"];

      if (Array.isArray(areasResponse)) {
        const areasFormatted = areasResponse.map((area) => {
          return {
            id: area.id._text,
            descripcion: area.descripcion._text,
            estado: area.estado._text,
            estadoStr: area.strEstado._text,
          };
        });
        setAreas(areasFormatted);
      }
    };

    getAreas();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await fetchRegisterMesa(idArea, descripcion, asientos, estado);
      alert("Mesa registrada exitosamente!");
      navigate("/mesas");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al registrar la mesa");
    }
  };

  return (
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
                  <h3 className="mb-4">Registrar Mesas! 🍴 </h3>
                  <p className="text-muted text-sm mb-5">Registro de mesas</p>
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
                        value={asientos}
                        onChange={(e) => setAsientos(e.target.value)}
                        required
                      />
                      <label>Asientos</label>
                    </div>
                    <div className="form-group row mt-3 d-flex justify-content-center">
                      <div className="col-sm-4 d-flex flex-column align-items-center">
                        <label className="mb-1 text-center">AREAS:</label>

                        <select
                          id="area"
                          className="form-control text-center"
                          value={idArea}
                          onChange={(e) => setIdArea(e.target.value)}
                        >
                          <option value="">Define el area</option>
                          {areas.map((area) => (
                            <option key={area.id} value={area.id}>
                              {area.descripcion}
                            </option>
                          ))}
                        </select>
                      </div>

                      <div className="col-sm-4 d-flex flex-column align-items-center">
                        <label className="mb-1 text-center">ESTADO</label>
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
  );
}

export default RegistroMesas;
