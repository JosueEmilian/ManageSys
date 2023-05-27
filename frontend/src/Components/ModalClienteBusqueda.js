import React, { useState } from "react";
import { Form, Button, FormControl } from "react-bootstrap";
import { fetchClienteSearch } from "../ServiceSoap/Cliente/SearchClienteSoap.js";

const ModalClienteBusqueda = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    nombre: "",
    nit: "",
    razon_social: "",
    nickname: "",
    direccion: "",
    telefono: "",
  });

  const [searchTerm, setSearchTerm] = useState("");

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleSearch = async () => {
    const searchResult = await fetchClienteSearch(searchTerm);

    if (searchResult) {
      const cliente =
        searchResult["S:Envelope"]["S:Body"]["ns2:buscarClienteResponse"][
          "return"
        ];
      setFormData({
        ...formData,
        nombre: cliente.nombre?._text || "",
        nit: cliente.nit?._text || "",
        razon_social: cliente.razonSocial?._text || "",
        nickname: cliente.nickname?._text || "",
        direccion: cliente.direccion?._text || "",
        telefono: cliente.telefono?._text || "",
      });
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(formData);
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group>
        <Form.Label>Buscar cliente por nit</Form.Label>
        <FormControl
          type="search"
          style={{ width: "200px" }}
          value={searchTerm}
          onChange={(event) => setSearchTerm(event.target.value)}
          placeholder="Ingrese el nit"
        />
      </Form.Group>
      <Button variant="warning" onClick={handleSearch} className="mt-2">
        Buscar
      </Button>
      <Form.Group controlId="formName">
        <Form.Label>Nombre</Form.Label>
        <Form.Control
          type="text"
          name="nombre"
          value={formData.nombre}
          onChange={handleInputChange}
          disabled
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Nit</Form.Label>
        <Form.Control
          type="text"
          name="nit"
          value={formData.nit}
          onChange={handleInputChange}
          disabled
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Razon Social</Form.Label>
        <Form.Control
          type="text"
          name="razon_social"
          value={formData.razon_social}
          onChange={handleInputChange}
          disabled
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>NickName</Form.Label>
        <Form.Control
          type="text"
          name="nickname"
          value={formData.nickname}
          onChange={handleInputChange}
          disabled
        />
      </Form.Group>
      <Form.Group controlId="forName">
        <Form.Label>Telefono</Form.Label>
        <Form.Control
          type="text"
          name="telefono"
          value={formData.telefono}
          onChange={handleInputChange}
          disabled
        />
      </Form.Group>
      <Button variant="danger" type="submit" className="mt-4 text-center">
        Seleccionar
      </Button>
    </Form>
  );
};

export default ModalClienteBusqueda;
