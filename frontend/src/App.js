import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Routes, Route, Link, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Usuarios from "./Components/Usuarios";
import { login, logout, resetUser } from "./Service/userAction";
import Dashboard from "./pages/Dashboard";
import ScrollTopTop from "./Components/ScrollTopTop";
import Navigation from "./Components/Navigation";

function App() {
  const dispatch = useDispatch();
  const email = useSelector((state) => state.email);
  const isAdmin = useSelector((state) => state.isAdmin);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.email) {
      dispatch(login(user.email, user.isAdmin));
    }
  }, [dispatch]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    dispatch(logout());
    dispatch(resetUser());
  };

  return (
    <BrowserRouter>
      <ScrollTopTop />
      <Navigation handleLogout={handleLogout} />
      <Routes>
        <Route index element={<Usuarios />} />
        <Route path="/usuarios" element={<Usuarios />} />

        {/* Si el usuario no está logueado, lo redirige a la página de login */}
        {!email && (
          <>
            <Route path="/login" element={<Login />} />
          </>
        )}

        {/* Si el usuario está logueado, lo redirige a la página de home */}
        {email && (
          <>
            <Route path="/home" element={<Home />} />
          </>
        )}

        {/* Si el usuario es Admin, lo redirige a la pagina de dashboard */}
        {isAdmin && (
          <>
            <Route path="/dashboard" element={<Dashboard />} />
          </>
        )}
        <Route path="*" element={<Usuarios />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
