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
    <div>
      <h2 className="text-center mt-4">Monitoreo de Areas:</h2>
      <div className="text-center mt-4">
        {areas
          .filter((area) => area.estado === "true")
          .map((area) => (
            <Button
              variant="warning"
              className="mx-2"
              key={area.id}
              onClick={() => handleAreaClick(area)}
            >
              {area.descripcion}
            </Button>
          ))}
      </div>

      {selectedArea && (
        <div>
          <h3 className="text-center mt-4">
            Mesas en el{" "}
            {areas.find((area) => area.id === selectedArea).descripcion}
          </h3>
          <div className="d-flex justify-content-center">
            {mesas.map((mesa) => (
              <Card
                className={`mesa ${
                  mesa.estado !== "false" ? "disponible" : "no-disponible"
                } mx-2 text-center`}
                key={mesa.id}
              >
                <Card.Body>
                  <Card.Title>{`ID: ${mesa.id} ${mesa.descripcion}`}</Card.Title>
                  <Card.Subtitle>{`Estado: ${mesa.estado}`}</Card.Subtitle>
                  <Card.Text>{`Asientos: ${mesa.asientos}`}</Card.Text>
                  {mesa.estado !== "false" ? (
                    <NavLink
                      to={`/monitoreo-pedido/asignar-pedido?id=${mesa.id}`}
                    >
                      <Button variant="danger">Registrar Pedido</Button>
                    </NavLink>
                  ) : (
                    <Button
                      className="no-disponible"
                      variant="secondary"
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