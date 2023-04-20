import React from "react";
import { useSelector } from "react-redux";
import { Card, Row, Col } from "react-bootstrap";
import "./Dashboard.css";
import usuario from "../images/usuario.jpg";
import modulo from "../images/modulo.jpg";
import permisos from "../images/permisos.jpg";
import rol from "../images/rol.jpg";

function Dashboard() {
  const user = useSelector((state) => state.user);

  return (
    <div className="principal-text">
      <h1>DASHBOARD</h1>
      {<h2>Bienvenido {user?.usuario}</h2>}

      <Row className="custom-margin">
        <Col className="px-2 mt-3">
          <a href="/dashboard/rol">
            <Card>
              <Card.Img variant="top" src={rol} />
              <Card.Body>
                <Card.Title>ROL</Card.Title>
                <Card.Text>Ingreso de roles</Card.Text>
              </Card.Body>
            </Card>
          </a>
        </Col>
        <Col className="px-2 mt-3">
          <a href="/dashboard/modulo">
            <Card>
              <Card.Img variant="top" src={modulo} />
              <Card.Body>
                <Card.Title>MODULO</Card.Title>
                <Card.Text>Ingreso de Modulos</Card.Text>
              </Card.Body>
            </Card>
          </a>
        </Col>
        <Col className="px-2 mt-3">
          <a href="/dashboard/user">
            <Card>
              <Card.Img variant="top" src={usuario} />
              <Card.Body>
                <Card.Title>USUARIOS</Card.Title>
                <Card.Text>Ingreso de Usuarios</Card.Text>
              </Card.Body>
            </Card>
          </a>
        </Col>
        <Col className="px-2 mt-3">
          <a href="/dashboard/permiso">
            <Card>
              <Card.Img variant="top" src={permisos} />
              <Card.Body>
                <Card.Title>PERMISOS</Card.Title>
                <Card.Text>Asignacion de Permisos</Card.Text>
              </Card.Body>
            </Card>
          </a>
        </Col>
      </Row>
    </div>
  );
}

export default Dashboard;
