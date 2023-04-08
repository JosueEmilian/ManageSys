import React from "react";
import "./Login.css";

function RegistroUsuarios() {
  return (
    <div className="App">
      <div className=" align-items-center py-4 bg-gray-100 vh-100">
        <div className="container">
          <div className="row align-items-center justify-content-center">
            {" "}
            <div className="show col-lg-6 px-lg-4">
              <div className="card">
                <div className="card-header px-lg-5">
                  <div className="card-heading text-center">
                    ManageSys Login
                  </div>
                </div>
                <div className="card-body p-lg-5">
                  <h3 className="mb-4">Registor de Usuarios! 👋 </h3>
                  <p className="text-muted text-sm mb-5">
                    El proyecto consiste en la elaboración de un sistema en
                    lenguaje de programación java con una arquitectura web
                    utilizando el patrón de diseño MVC, debe manejar la parte de
                    la configuración y seguridad del sistema.
                  </p>
                  <form id="loginForm" action="index.html">
                    <div className="form-floating mb-3">
                      <input className="form-control" type="email" required />
                      <label for="floatingInput">Email</label>
                    </div>
                    <div className="form-floating mb-3">
                      <input
                        className="form-control"
                        type="password"
                        required
                      />
                      <label for="floatingPassword">Password</label>
                    </div>
                    <div className="form-check mb-3">
                      <input className="form-check-input" type="checkbox" />
                      <label className="form-check-label" for="remember">
                        Recordar contraseña
                      </label>
                    </div>
                    <button className="btn btn-warning" type="submit">
                      Ingresar
                    </button>
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
