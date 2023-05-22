import xml2js from "xml-js";

export const fetchRegisterCliente = async (
  nombre,
  nit,
  razonSocial,
  nickname,
  direccion,
  telefono
) => {
  try {
    const response = await fetch("http://localhost:8080/backend/WSArea?WSDL", {
      method: "POST",
      headers: {
        "Content-Type": "text/xml;charset=UTF-8",
      },
      body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <insertarCliente xmlns="http://service/">
              <nombre xmlns="">${nombre}</nombre>
              <nit xmlns="">${nit}</nit>
              <razonSocial xmlns="">${razonSocial}</razonSocial>
              <nickname xmlns="">${nickname}</nickname>
              <direccion xmlns="">${direccion}</direccion>
              <telefono xmlns="">${telefono}</telefono>
          </insertarCliente>
      </Body>
  </Envelope>`,
    });
    const data = await response.text();
    const json = xml2js.xml2js(data, { compact: true, spaces: 4 });
    return json;
  } catch (error) {
    console.log(error);
  }
};
