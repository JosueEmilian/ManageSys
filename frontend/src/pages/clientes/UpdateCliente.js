import React, { useEffect, useState } from "react";
import "../Login.css";
import { fetchUpdateCliente } from "../../ServiceSoap/Cliente/UpdateClienteSoap.js";
import { fetchClienteID } from "../../ServiceSoap/Cliente/GetClienteIDSoap.js";
import { useLocation, useNavigate } from "react-router-dom";

function RegistroAreas() {
  const navigate = useNavigate();

  // Recuperamos ID SELECCIONADO y mostramos sus datos en los inputs
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  //Variables de registro clientes
  const [nombre, setNombre] = useState("");
  const [nit, setNit] = useState("");
  const [razonSocial, setRazonSocial] = useState("");
  const [nickname, setNickname] = useState("");
  const [direccion, setDireccion] = useState("");
  const [telefono, setTelefono] = useState("");

  // CARGA DE DATOS DE CLIENTE
  const [clientesData, setClientesData] = useState({});
  const mapeoDatos = {
    nombre: setNombre,
    nit: setNit,
    razonSocial: setRazonSocial,
    nickname: setNickname,
    direccion: setDireccion,
    telefono: setTelefono,
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchClienteID(id);
        const clientesResponse =
          response["S:Envelope"]["S:Body"]["ns2:getClienteIdResponse"][
            "return"
          ];
        setClientesData(clientesResponse); // Almacena los datos en el estado local

        Object.entries(mapeoDatos).forEach(([field, setter]) => {
          if (clientesResponse[field]?._text) {
            setter(clientesResponse[field]._text); // Sincroniza el valor del campo
          }
        });
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [id]);

  //para Actualizar el cliente
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await fetchUpdateCliente(
        nombre,
        nit,
        razonSocial,
        nickname,
        direccion,
        telefono,
        id
      );
      alert("Clientes actualizado exitosamente!");
      navigate("/clientes");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al actualizar el cliente.");
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
                  <h3 className="mb-4">Actualizacion de Clientes! 🧑‍💻 </h3>
                  <p className="text-muted text-sm mb-5">
                    Acttualizacion de Clientes
                  </p>
                  <form onSubmit={handleSubmit}>
                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                      />
                      <label>Nombre</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={nit}
                        onChange={(e) => setNit(e.target.value)}
                        required
                      />
                      <label>Nit</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={razonSocial}
                        onChange={(e) => setRazonSocial(e.target.value)}
                        required
                      />
                      <label>Razon Social</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={nickname}
                        onChange={(e) => setNickname(e.target.value)}
                        required
                      />
                      <label>NickName</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={direccion}
                        onChange={(e) => setDireccion(e.target.value)}
                        required
                      />
                      <label>Direccion</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={telefono}
                        onChange={(e) => setTelefono(e.target.value)}
                        required
                      />
                      <label>Telefono</label>
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

export default RegistroAreas;
