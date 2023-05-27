import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";

const ModalCliente = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    nombre: "",
    nit: "",
    razon_social: "",
    nickname: "",
    direccion: "",
    telefono: "",
  });

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(formData);
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formName">
        <Form.Label>Nombre</Form.Label>
        <Form.Control
          type="text"
          name="nombre"
          value={formData.nombre}
          onChange={handleInputChange}
          required
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Nit</Form.Label>
        <Form.Control
          type="text"
          name="nit"
          value={formData.nit}
          onChange={handleInputChange}
          required
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Razon Social</Form.Label>
        <Form.Control
          type="text"
          name="razon_social"
          value={formData.razon_social}
          onChange={handleInputChange}
          required
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>NickName</Form.Label>
        <Form.Control
          type="text"
          name="nickname"
          value={formData.nickname}
          onChange={handleInputChange}
          required
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Direccion</Form.Label>
        <Form.Control
          type="text"
          name="direccion"
          value={formData.direccion}
          onChange={handleInputChange}
          required
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Telefono</Form.Label>
        <Form.Control
          type="text"
          name="telefono"
          value={formData.telefono}
          onChange={handleInputChange}
          required
        />
      </Form.Group>

      <Button variant="danger" type="submit" className="mt-4 text-center">
        Registrar
      </Button>
    </Form>
  );
};

export default ModalCliente;
