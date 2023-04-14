import React, { useState, useEffect } from "react";
import { Badge, Table, Form, FormControl } from "react-bootstrap";
import { ReadUserSoap } from "../../ServiceSoap/User/ReadUserSoap.js";
import { DeleteUserId } from "../../ServiceSoap/User/DeleteUserIDSoap.js";
import "./ReadUsuarios.css";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { searchUserSoap } from "../../ServiceSoap/User/SearchUserSoap.js";
import { NavLink } from "react-router-dom";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";

function ReadUsuarios() {
  const [users, setUsers] = useState([]);
  const [filter, setFilter] = useState("");
  const [searchTerm, setSearchTerm] = useState("");
  const [showList, setShowList] = useState(true);
  const [originalUsers, setOriginalUsers] = useState([]);

  //UseEffect que carga los usuarios utilizando ReadUserSoap si la variable de estado "users" está vacía.
  useEffect(() => {
    const getUsers = async () => {
      try {
        const users = await ReadUserSoap();
        setUsers(users);
        setOriginalUsers(users);
      } catch (error) {
        console.log(error);
      }
    };
    if (users.length === 0) {
      // Solo hace la solicitud si no hay users en la variable de estado
      getUsers();
    }
  }, [users]);

  //Filtrar usuarios
  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const filteredUsers = users.filter((user) =>
    user.rol.toLowerCase().includes(filter.toLowerCase())
  );

  //lista de roles
  const roles = [...new Set(users.map((user) => user.rol))];

  //Para funcion busqueda
  const handleSearch = async () => {
    try {
      let users;
      if (searchTerm === "") {
        users = originalUsers;
      } else {
        users = await searchUserSoap(searchTerm);
      }
      setUsers(users);
      setShowList(true);
    } catch (error) {
      console.log(error);
    }
  };

  //funcion para eliminar usuario
  async function handleDelete(id) {
    const response = await DeleteUserId(id);
    if (response) {
      alert("Eliminado correctamente");
      window.location.reload();
    } else {
      // manejar el error
    }
  }

  return (
    <div className="table-principal ">
      <h1 className="text-center">Usuarios</h1>

      <div className="text-center mt-4">
        <NavLink
          to="/dashboard/user/register"
          className="btn btn-warning text-white"
        >
          <FontAwesomeIcon
            icon={faUserPlus}
            style={{ color: "white", fontSize: "25px", margin: "0 10px" }}
          />
          Agregar
        </NavLink>
      </div>

      <Form className="d-flex mr-20">
        <Form.Group controlId="formBasicFilter" className="flex-grow-1">
          <Form.Label>Filtrar por rol:</Form.Label>
          <Form.Control
            className="w-auto"
            as="select"
            value={filter}
            onChange={handleFilterChange}
          >
            <option value="">Todos</option>
            {roles.map((role) => (
              <option key={role} value={role}>
                {role}
              </option>
            ))}
          </Form.Control>
        </Form.Group>

        <Form.Group>
          <Form.Label>Buscar (EMAIL O USUARIO)</Form.Label>
          <FormControl
            type="search"
            style={{ width: "200px" }}
            value={searchTerm}
            onChange={(event) => setSearchTerm(event.target.value)}
            placeholder="Ingrese Email o usuario"
            onKeyDown={(event) => {
              if (event.key === "Enter") {
                event.preventDefault();
                handleSearch();
              }
            }}
          />
        </Form.Group>
        <button
          type="button"
          className="btn btn-warning mx-3"
          onClick={handleSearch}
        >
          Buscar
        </button>
      </Form>
      {showList && (
        <Table hover responsive className="table-principal">
          <thead className="text-center">
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Usuario</th>
              <th>Email</th>
              <th>Rol</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody className="text-center">
            {filteredUsers.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.nombre}</td>
                <td>{user.apellido}</td>
                <td>{user.usuario}</td>
                <td>{user.email}</td>
                <td>
                  <Badge bg="warning" pill>
                    {user.rol}
                  </Badge>
                </td>
                <td>{user.estado}</td>
                <td>
                  <button
                    type="button"
                    className="btn btn-outline-danger mx-3"
                    onClick={() => handleDelete(user.id)}
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </button>

                  <NavLink
                    to={`/dashboard/user/edit?id=${user.id}`}
                    className="btn btn-warning text-white"
                  >
                    <FontAwesomeIcon
                      style={{ color: "white" }}
                      icon={faPenToSquare}
                    />
                    Editar
                  </NavLink>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </div>
  );
}

export default ReadUsuarios;
