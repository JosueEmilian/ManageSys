import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import HomePublic from "./pages/HomePublic";
import Navigation from "./Components/Navigation";
import Dashboard from "./pages/Dashboard";
import AnalistaDatos from "./pages/AnalistaDatos.js";
import RegistroUsuarios from "./pages/RegistroUsuarios";
import ReadUsuarios from "./pages/ReadUsuarios.js";
import { login, logout, resetUser } from "./Service/userAction";
import ScrollTopTop from "./Components/ScrollTopTop";

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
  };

  const path = user?.ruta;
  const Ruta = rutas[path];
  console.log("path: ", path);

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
        {/* Si el usuario está logueado, lo redirige a la página de home */}
        {isLoggedIn && (
          <>
            <Route path={path} element={<Ruta />} />
          </>
        )}
        {/* Si el usuario es un Administrador */}
        {user?.rol === "Administrador" && (
          <>
            <Route path="/dashboard/user" element={<ReadUsuarios />} />
            <Route
              path="/dashboard/user/register"
              element={<RegistroUsuarios />}
            />
          </>
        )}

        <Route path="*" element={<HomePublic />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
