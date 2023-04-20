import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { useDispatch, useSelector } from "react-redux";
import { LinkContainer } from "react-router-bootstrap";
import { resetUser, logout } from "../Service/userAction";
import "./Navigation.css";

function Navigation() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);

  const handleLogout = () => {
    localStorage.removeItem("user"); // borra el usuario del almacenamiento local
    dispatch(logout()); // llama a la acción de logout
    dispatch(resetUser()); // llama a la acción de resetUser
  };

  return (
    <Navbar className="bg-warning" expand="lg">
      <Container>
        <LinkContainer to="/">
          <Navbar.Brand>MANAGE SYS</Navbar.Brand>
        </LinkContainer>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            {/* Si no existe usuario */}
            {!user?.usuario && (
              <LinkContainer to="/login">
                <Nav.Link>Iniciar Sesion</Nav.Link>
              </LinkContainer>
            )}

            {/* Si existe usuario */}
            {user?.usuario && (
              <NavDropdown title={`${user.usuario}`} id="basic-nav-dropdown">
                {/* Si el usuario es admin */}
                {user?.rol === "Administrador" && (
                  <>
                    <LinkContainer to="/dashboard">
                      <NavDropdown.Item>Dashboard</NavDropdown.Item>
                    </LinkContainer>
                    <LinkContainer to="/">
                      <NavDropdown.Item>Home</NavDropdown.Item>
                    </LinkContainer>
                  </>
                )}

                {/* Si el usuario es Analista */}
                {user?.rol === "Analista" && (
                  <>
                    <LinkContainer to="/analisis-datos">
                      <NavDropdown.Item>Analisis de Datos</NavDropdown.Item>
                    </LinkContainer>
                  </>
                )}

                {user?.rol !== "Administrador" && (
                  <>
                    <LinkContainer to="/home">
                      <NavDropdown.Item>Home</NavDropdown.Item>
                    </LinkContainer>
                  </>
                )}

                <NavDropdown.Divider />
                <div className="text-center logout-btn">
                  <Button variant="warning" onClick={handleLogout}>
                    Cerrar Sesion
                  </Button>
                </div>
              </NavDropdown>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navigation;
