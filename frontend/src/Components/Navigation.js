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
  const email = useSelector((state) => state.email);
  const isAdmin = useSelector((state) => state.isAdmin);

  const handleLogout = () => {
    localStorage.removeItem("user"); // borra el usuario del almacenamiento local
    dispatch(logout()); // llama a la acción de logout
    dispatch(resetUser()); // llama a la acción de resetUser
    console.log("isAdmin: ", isAdmin);
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
            {!email && (
              <LinkContainer to="/login">
                <Nav.Link>Iniciar Sesion</Nav.Link>
              </LinkContainer>
            )}

            {/* Si existe usuario */}
            {email && (
              <NavDropdown title={`${email}`} id="basic-nav-dropdown">
                {/* Si el usuario es admin */}
                {isAdmin && (
                  <>
                    <LinkContainer to="/dashboard">
                      <NavDropdown.Item>Dashboard</NavDropdown.Item>
                    </LinkContainer>

                    <LinkContainer to="/config">
                      <NavDropdown.Item>Configuraciones</NavDropdown.Item>
                    </LinkContainer>
                  </>
                )}

                {!isAdmin && (
                  <>
                    <LinkContainer to="/home">
                      <NavDropdown.Item>Perfil</NavDropdown.Item>
                    </LinkContainer>

                    <LinkContainer to="/ordenes">
                      <NavDropdown.Item>Ordenes</NavDropdown.Item>
                    </LinkContainer>
                  </>
                )}

                <NavDropdown.Divider />
                <Button
                  variant="warning"
                  onClick={handleLogout}
                  className="logout-btn"
                >
                  Cerrar Sesion
                </Button>
              </NavDropdown>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navigation;
