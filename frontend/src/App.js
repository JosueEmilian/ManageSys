import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Routes, Route, Link, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Usuarios from "./Components/Usuarios";
import { login, logout, resetUser } from "./Service/userAction";

function App() {
  const dispatch = useDispatch();
  const email = useSelector((state) => state.email);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.email) {
      dispatch(login(user.email));
    }
  }, [dispatch]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    dispatch(logout());
    dispatch(resetUser());
    dispatch({ type: "CLEAR_EMAIL" });
  };

  return (
    <BrowserRouter>
      <Navigation handleLogout={handleLogout} />
      <Routes>
        <Route index element={<Usuarios />} />
        <Route path="/usuarios" element={<Usuarios />} />

        <Route
          path="/login"
          element={!email ? <Login /> : <Navigate to="/home" />}
        />
        <Route
          path="/home"
          element={email ? <Home /> : <Navigate to="/login" />}
        />
      </Routes>
    </BrowserRouter>
  );
}

function Navigation() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/home">Home</Link>
        </li>
        <li>
          <Link to="/usuarios">Usuarios</Link>
        </li>
      </ul>
    </nav>
  );
}

export default App;
