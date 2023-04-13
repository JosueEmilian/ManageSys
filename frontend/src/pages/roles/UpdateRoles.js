import React, { useEffect, useState } from "react";
import "../Login.css";
import { useLocation, useNavigate } from "react-router-dom";
import GetRolesIDSoap from "../../ServiceSoap/Rol/GetRolIDSoap.js";
import { UpdateRolSoap } from "../../ServiceSoap/Rol/UpdateRolSoap.js";

function UpdateRoles() {
  const navigate = useNavigate();

  //Variables de registro usuario
  const [estado, setEstado] = useState("");

  //Recuperamos ID SELECCIONADO, Y MOSTRAMOS SUS DATOS EN LOS INPUTS
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get("id");

  const [rol, setRol] = useState({
    id: "",
    nombre: "",
    descripcion: "",
    estado: "",
  });

  useEffect(() => {
    async function fetchUser() {
      try {
        const result = await GetRolesIDSoap(id);
        setRol(result[0]);
      } catch (error) {
        console.error(error);
      }
    }
    fetchUser();
  }, [id]);

  //PARA REGISTRAR EL USUARIO
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await UpdateRolSoap(rol.nombre, rol.descripcion, estado, id);
      alert("Usuario actualizado exitosamente!");
      navigate("/dashboard/rol");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al registrar el rol.");
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
                    ManageSys Login
                  </div>
                </div>
                <div className="card-body p-lg-5">
                  <h3 className="mb-4">Registrar Roles! 🧑‍💻 </h3>
                  <p className="text-muted text-sm mb-5">Registro de roles</p>
                  <form onSubmit={handleSubmit}>
                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={rol.nombre}
                        onChange={(event) =>
                          setRol({ ...rol, nombre: event.target.value })
                        }
                        required
                      />
                      <label for="floatingInput">Nombre</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={rol.descripcion}
                        onChange={(event) =>
                          setRol({ ...rol, descripcion: event.target.value })
                        }
                        required
                      />
                      <label for="floatingPassword">Descripcion</label>
                    </div>

                    <div className="form-group row mt-3 d-flex justify-content-center">
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

                    <div className="mt-3 align-item-center justify-content-center form-group row">
                      <button className="btn btn-warning " type="submit">
                        Ingresar
                      </button>
                    </div>
                  </form>
                </div>
                <div className="card-footer px-lg-5 py-lg-4 ">
                  <div className="text-center text-muted">
                    Administrar la configuracion y seguridad del sistema
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

export default UpdateRoles;
