import React, { useEffect, useState } from "react";
import "../Login.css";
import ReadRoleSoap from "../../ServiceSoap/Rol/ReadRolSoap.js";
import { RegisterUserSoap } from "../../ServiceSoap/User/RegisterUserSoap";
import { useNavigate } from "react-router-dom";

function RegistroUsuarios() {
  const [roles, setRoles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  //UseEffect que carga los roles utilizando si la variable de estado "roles" está vacía.
  useEffect(() => {
    async function loadRoles() {
      try {
        const rolesData = await ReadRoleSoap();
        setRoles(rolesData);
      } catch (error) {
        console.log(error);
        setError(true);
      } finally {
        setLoading(false);
      }
    }

    if (roles.length === 0) {
      // Solo hace la solicitud si no hay roles en la variable de estado
      loadRoles();
    }
  }, [roles]);

  //Variables de registro usuario
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [usuario, setUsuario] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rol, setRol] = useState("");
  const [estado, setEstado] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await RegisterUserSoap(
        nombre,
        apellido,
        usuario,
        email,
        password,
        rol,
        estado
      );
      alert("Usuario registrado exitosamente!");
      navigate("/dashboard/user");
    } catch (error) {
      console.error(error);
      alert(
        "Hubo un error al registrar al usuario. Por favor, intenta de nuevo más tarde."
      );
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
                  <h3 className="mb-4">Registrar Usuarios! 🧑‍💻 </h3>
                  <p className="text-muted text-sm mb-5">
                    Registro de usuarios
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
                      <label for="floatingInput">Nombre</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={apellido}
                        onChange={(e) => setApellido(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">Apellido</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="text"
                        value={usuario}
                        onChange={(e) => setUsuario(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">Usuario</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">Email</label>
                    </div>

                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                      />
                      <label for="floatingPassword">Password</label>
                    </div>

                    <div className="form-group row mt-3 d-flex justify-content-center">
                      <div className="col-sm-4 d-flex flex-column align-items-center">
                        <label htmlFor="rol" className="mb-1 text-center">
                          ROL:
                        </label>
                        {error && <p>Error al cargar los roles</p>}
                        {loading ? (
                          <p>Cargando roles...</p>
                        ) : (
                          <select
                            id="rol"
                            className="form-control text-center"
                            value={rol}
                            onChange={(e) => setRol(e.target.value)}
                          >
                            <option value="">Define el rol</option>
                            {roles.map((rol) => (
                              <option key={rol.id} value={rol.id}>
                                {rol.nombre}
                              </option>
                            ))}
                          </select>
                        )}
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

export default RegistroUsuarios;
