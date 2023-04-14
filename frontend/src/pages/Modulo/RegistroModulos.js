import React, { useEffect, useState } from "react";
import "../Login.css";
import ReadModulosSoap from "../../ServiceSoap/Modulo/ReadModuloSoap.js";
import { RegisterModuloSoap } from "../../ServiceSoap/Modulo/RegisterModuloSoap.js";

import { useNavigate } from "react-router-dom";

function RegistrarModulo() {
  const navigate = useNavigate();
  const [modulos, setModulos] = useState([]);
  const [originalModulos, setOriginalModulos] = useState([]);

  //UseEffect que carga ID_MODULO_PADRE (NOMBRE) utilizando ReadModuloSoap si la variable de estado "modulos" está vacía.
  useEffect(() => {
    const getModulos = async () => {
      try {
        const modulos = await ReadModulosSoap();
        setModulos(modulos);
        setOriginalModulos(modulos);
      } catch (error) {
        console.log(error);
      }
    };
    if (originalModulos.length === 0) {
      // Solo hace la solicitud si no hay modulos en la variable de estado
      getModulos();
    }
  }, [originalModulos]);

  //Variables de registro usuario
  const [nombre, setNombre] = useState("");
  const [path, setPah] = useState("");
  const [nivel, setNivel] = useState("");
  const [idPadre, setIdPadre] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await RegisterModuloSoap(nombre, path, nivel, idPadre);
      alert("Modulo registrado exitosamente!");
      navigate("/dashboard/modulo");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al registrar el modulo");
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
                  <h3 className="mb-4">Registrar Modulos! 🧑‍💻 </h3>
                  <p className="text-muted text-sm mb-5">Registro de roles</p>
                  <form onSubmit={handleSubmit}>
                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                      />
                      <label for="floatingInput">Nombre</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={path}
                        onChange={(e) => setPah(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">PATH</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={nivel}
                        onChange={(e) => setNivel(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">Nivel</label>
                    </div>

                    <div className="form-group row mt-3 d-flex justify-content-center">
                      <div className="col-sm-4 d-flex flex-column align-items-center">
                        <label htmlFor="estado" className="mb-1 text-center">
                          SELECCIONA EL MODULO PADRE
                        </label>
                        <select
                          id="estado"
                          className="form-control text-center"
                          value={idPadre}
                          onChange={(event) => setIdPadre(event.target.value)}
                        >
                          <option value="">Ninguno</option>
                          {modulos.map((modulo) => (
                            <option key={modulo.id} value={modulo.id}>
                              {modulo.nombre + "--> Nivel: " + modulo.nivel}
                            </option>
                          ))}
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

export default RegistrarModulo;
