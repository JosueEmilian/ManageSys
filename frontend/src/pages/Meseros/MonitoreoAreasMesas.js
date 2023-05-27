import React, { useState, useEffect } from "react";
import { fetchAreas } from "../../ServiceSoap/Area/ReadAreaSoap";
import { fetchMesasAreas } from "../../ServiceSoap/Mesa/ReadMesasAreaSoap";
import { NavLink } from "react-router-dom";
import { Button, Card } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./MonitoreoPedido.css";

const MesasPorArea = () => {
  const [selectedArea, setSelectedArea] = useState(null);
  const [mesas, setMesas] = useState([]);

  // CARGA DE AREAS
  const [areas, setAreas] = useState([]);
  useEffect(() => {
    const getAreas = async () => {
      const response = await fetchAreas();
      const areasResponse =
        response["S:Envelope"]["S:Body"]["ns2:listarAreasResponse"]["return"];

      if (Array.isArray(areasResponse)) {
        const areasFormatted = areasResponse.map((area) => {
          return {
            id: area.id._text,
            descripcion: area.descripcion._text,
            estado: area.estado._text,
          };
        });
        setAreas(areasFormatted);
      }
    };

    getAreas();
  }, []);

  // CARGA DE MESAS
  const handleAreaClick = async ({ id }) => {
    setSelectedArea(id);
    try {
      const response = await fetchMesasAreas(parseInt(id));
      if (response) {
        const mesasResponse =
          response["S:Envelope"]["S:Body"]["ns2:listarMesasDeAreaResponse"][
            "return"
          ];
        let mesasFormatted = Array.isArray(mesasResponse)
          ? mesasResponse.map((mesa) => {
              return {
                id: mesa.id._text,
                descripcion: mesa.descripcion._text,
                asientos: mesa.asientos._text,
                estado: mesa.estado._text,
              };
            })
          : [
              {
                id: mesasResponse.id._text,
                descripcion: mesasResponse.descripcion._text,
                asientos: mesasResponse.asientos._text,
                estado: mesasResponse.estado._text,
              },
            ];
        setMesas(mesasFormatted);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="area-interface">
      <h2 className="title">Monitoreo de Áreas:</h2>
      <div className="area-container">
        {areas
          .filter((area) => area.estado === "true")
          .map((area) => (
            <Button
              variant="warning"
              className="area-button"
              key={area.id}
              onClick={() => handleAreaClick(area)}
            >
              {area.descripcion}
            </Button>
          ))}
      </div>

      {selectedArea && (
        <div>
          <h3 className="selected-area">
            Mesas en el{" "}
            {areas.find((area) => area.id === selectedArea).descripcion}
          </h3>
          <div className="mesa-container">
            {mesas.map((mesa) => (
              <Card
                className={`mesa-card ${
                  mesa.estado !== "false" ? "disponible" : "no-disponible"
                }`}
                key={mesa.id}
              >
                <Card.Body>
                  <Card.Title className="title-desc">{`${mesa.descripcion}`}</Card.Title>
                  <Card.Text className="title-desc-estado">{`${
                    mesa.estado !== "false" ? "Disponible" : "Ocupada"
                  }`}</Card.Text>
                  <Card.Text>{`Asientos: ${mesa.asientos}`}</Card.Text>

                  {mesa.estado !== "false" ? (
                    <NavLink
                      to={`/monitoreo-pedido/asignar-pedido?id=${mesa.id}`}
                    >
                      <Button
                        variant="danger"
                        className="registrar-pedido-button"
                      >
                        Registrar Pedido
                      </Button>
                    </NavLink>
                  ) : (
                    <Button
                      variant="danger"
                      className="registrar-pedido-button"
                      disabled
                    >
                      Mesa no disponible
                    </Button>
                  )}
                </Card.Body>
              </Card>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default MesasPorArea;
