import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { login, logout, resetUser } from "./Service/userAction";
import Home from "./pages/Home";
import Login from "./pages/Login";
import HomePublic from "./pages/HomePublic";
import Navigation from "./Components/Navigation";
import Dashboard from "./pages/Dashboard";
import AnalistaDatos from "./pages/AnalistaDatos.js";
import RegistroUsuarios from "./pages/Usuarios/RegistroUsuarios";
import UpdateUsuarios from "./pages/Usuarios/UpdateUsuarios";
import ReadUsuarios from "./pages/Usuarios/ReadUsuarios.js";
import ReadRoles from "./pages/roles/ReadRoles";
import ScrollTopTop from "./Components/ScrollTopTop.js";
import RegistroRoles from "./pages/roles/RegistroRoles.js";
import UpdateRoles from "./pages/roles/UpdateRoles.js";
import ReadModulos from "./pages/Modulo/ReadModulos.js";
import RegistroModulos from "./pages/Modulo/RegistroModulos.js";
import UpdateModulo from "./pages/Modulo/UpdateModulos.js";
import ReadPermisos from "./pages/Permisos/ReadPermisos.js";
import RegistrarPermiso from "./pages/Permisos/RegistroPermisos.js";
import UpdatePermiso from "./pages/Permisos/UpdatePermisos.js";
import MonitoreoPedido from "./pages/Meseros/MonitoreoPedido.js";
import AsignarPedido from "./pages/Meseros/AsignarPedido.js";
import ReadAreas from "./pages/Areas/ReadAreas.js";
import RegistroAreas from "./pages/Areas/RegistroAreas.js";
import UpdateArea from "./pages/Areas/UpdateArea.js";
import ReadMesas from "./pages/Mesas/ReadMesas.js";
import RegistroMesas from "./pages/Mesas/RegistroMesas.js";
import UpdateMesa from "./pages/Mesas/UpdateMesa.js";
import ReadClientes from "./pages/clientes/ReadClientes.js";

function App() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const isLoggedIn = !!user;

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    if (storedUser) {
      dispatch(login(storedUser));
    }
  }, [dispatch]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    dispatch(logout());
    dispatch(resetUser());
  };

  // Rutas principales
  const rutas = {
    "/dashboard": Dashboard,
    "/home": Home,
    "/analisis-datos": AnalistaDatos,
    "/monitoreo-pedido": MonitoreoPedido,
  };

  //Logica segun su modulo tabla db
  const path = user?.ruta;
  const Ruta = rutas[path];
  // console.log("path: ", path);

  return (
    <BrowserRouter>
      <ScrollTopTop />
      <Navigation isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
      <Routes>
        <Route index element={<HomePublic />} />

        {/* Si el usuario no está logueado, lo redirige a la página de login */}
        {!isLoggedIn && (
          <>
            <Route path="/login" element={<Login />} />
          </>
        )}
        {/* Si el usuario está logueado, lo redirige a la página de home dependiendo su rol */}
        {isLoggedIn && (
          <>
            <Route path={path} element={<Ruta />} />
          </>
        )}
        {/* Si el usuario es un Administrador */}
        {user?.rol === "Administrador" && (
          <>
            {/* RUTAS PARA MANTENIMIENTO DE USUARIOS */}
            <Route path="/dashboard/user" element={<ReadUsuarios />} />
            <Route
              path="/dashboard/user/register"
              element={<RegistroUsuarios />}
            />
            <Route path="/dashboard/user/edit" element={<UpdateUsuarios />} />

            {/* RUTAS PARA MANTENIMIENTO DE LOS ROLES */}
            <Route path="/dashboard/rol" element={<ReadRoles />} />
            <Route path="/dashboard/rol/register" element={<RegistroRoles />} />
            <Route path="/dashboard/rol/edit" element={<UpdateRoles />} />

            {/* RUTAS PARA MANTENIMIENTO MODULOS */}
            <Route path="/dashboard/modulo" element={<ReadModulos />} />
            <Route
              path="/dashboard/modulo/register"
              element={<RegistroModulos />}
            />
            <Route path="/dashboard/modulo/edit" element={<UpdateModulo />} />

            {/* RUTAS PARA MANTENIMIENTO PERMISOS */}
            <Route path="/dashboard/permiso" element={<ReadPermisos />} />
            <Route
              path="/dashboard/permiso/register"
              element={<RegistrarPermiso />}
            />
            <Route path="/dashboard/permiso/edit" element={<UpdatePermiso />} />
          </>
        )}

        {/* Si el usuario es un Mesero */}
        {/* Rutas mantenimiento MESERO */}
        {user?.rol === "Mesero" && (
          <>
            <Route path="/monitoreo-pedido" element={<MonitoreoPedido />} />
            <Route
              path="/monitoreo-pedido/asignar-pedido"
              element={<AsignarPedido />}
            />

            {/* RUTAS PARA MANTENIMIENTO DE AREAS */}
            <Route path="/areas" element={<ReadAreas />} />
            <Route path="/areas/areas-register" element={<RegistroAreas />} />
            <Route path="/areas/areas-edit" element={<UpdateArea />} />

            {/* RUTAS PARA MANTENIMIENTO DE MESAS */}
            <Route path="/mesas" element={<ReadMesas />} />
            <Route path="/mesas/mesas-register" element={<RegistroMesas />} />
            <Route path="/mesas/mesas-edit" element={<UpdateMesa />} />

            {/* RUTAS PARA MANTENIMIENTO DE CLIENTES */}
            <Route path="/clientes" element={<ReadClientes />} />
          </>
        )}

        <Route path="*" element={<HomePublic />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
