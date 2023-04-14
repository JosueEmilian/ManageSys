import React, { useEffect, useState } from "react";
import "../Login.css";
import ReadModulosSoap from "../../ServiceSoap/Modulo/ReadModuloSoap.js";
import ReadRoleSoap from "../../ServiceSoap/Rol/ReadRolSoap.js";
import { RegistrarPermisoSoap } from "../../ServiceSoap/Permisos/RegisterPermisosSoap.js";
import ModalModulos from "../../Components/ModalModulos.js";
import ModalRoles from "../../Components/ModalRoles.js";

import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

function RegistrarPermiso() {
  const navigate = useNavigate();
  const [modulos, setModulos] = useState([]);
  const [roles, setRoles] = useState([]);
  const [originalModulos, setOriginalModulos] = useState([]);
  const [originalRoles, setOriginalRoles] = useState([]);

  //UseEffect que carga la DATA MODULOS  utilizando ReadModuloSoap si la variable de estado "modulos" está vacía.
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

  //MODAL QUE MUESTRA LOS MODULOS SELECCIONABLES
  const [showModal, setShowModal] = useState(false);
  const [selectedId, setSelectedId] = useState(null);

  const handleModalSelect = (id) => {
    setSelectedId(id);
    setIdModulo(id); // Actualiza el ID del módulo seleccionado
    setShowModal(false);
  };

  //UseEffect que carga la DATA ROLES  utilizando ReadRoleSoap si la variable de estado "roles" está vacía.
  useEffect(() => {
    const getRoles = async () => {
      try {
        const roles = await ReadRoleSoap();
        setRoles(roles);
        setOriginalRoles(roles);
      } catch (error) {
        console.log(error);
      }
    };
    if (originalRoles.length === 0) {
      // Solo hace la solicitud si no hay roles en la variable de estado
      getRoles();
    }
  }, [originalRoles]);

  //MODAL QUE MUESTRA LOS ROLES SELECCIONABLES
  const [showRol, setShowRol] = useState(false);
  const [selectedIdRol, setSelectedIdRol] = useState(null);

  const handleModalSelectRol = (id) => {
    setSelectedIdRol(id);
    setIdRol(id);
    setShowRol(false);
  };

  //ASIGNACION O REGISTRO DE PERMISOS
  const [idModulo, setIdModulo] = useState("");
  const [idRol, setIdRol] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await RegistrarPermisoSoap(idModulo, idRol);
      alert("Permiso Asignado exitosamente!");
      navigate("/dashboard/permiso");
    } catch (error) {
      console.error(error);
      alert("Hubo un error al asignar el permiso");
    }
  };

  return (
    <>
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
                    <h3 className="mb-4">Asignar Permisos! 🧑‍💻 </h3>
                    <p className="text-muted text-sm mb-5">
                      Asignacion de permisos
                    </p>
                    <form onSubmit={handleSubmit}>
                      <div className="form-floating mb-3">
                        <Button
                          variant="danger"
                          onClick={() => setShowRol(true)}
                        >
                          Seleccionar Rol
                        </Button>
                      </div>

                      <div className="form-floating mb-3">
                        <Button
                          variant="danger"
                          onClick={() => setShowModal(true)}
                        >
                          Seleccionar Modulo
                        </Button>
                      </div>

                      <ModalModulos
                        show={showModal}
                        onHide={() => setShowModal(false)}
                        data={modulos}
                        onSelect={handleModalSelect}
                      />

                      <ModalRoles
                        show={showRol}
                        onHide={() => setShowRol(false)}
                        data={roles}
                        onSelect={handleModalSelectRol}
                      />

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
    </>
  );
}

export default RegistrarPermiso;
