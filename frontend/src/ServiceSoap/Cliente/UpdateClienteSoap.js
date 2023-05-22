import xml2js from "xml-js";

export const fetchUpdateCliente = async (
  nombre,
  nit,
  nickname,
  razonsocial,
  telefono,
  direccion,
  id
) => {
  try {
    const response = await fetch(
      "http://localhost:8080/backend/WsClienteNuevo?WSDL",
      {
        method: "POST",
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
        body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <actualizarUsuario xmlns="http://service/">
                <nombre xmlns="">${nombre}</nombre>
                <nit xmlns="">${nit}</nit>
                <nickname xmlns="">${nickname}</nickname>
                <razonSocial xmlns="">${razonsocial}</razonSocial>
                <telefono xmlns="">${telefono}</telefono>
                <direccion xmlns="">${direccion}</direccion>
                <id xmlns="">${id}</id>
            </actualizarUsuario>
        </Body>
    </Envelope>`,
      }
    );
    const data = await response.text();
    const json = xml2js.xml2js(data, { compact: true, spaces: 4 });
    return json;
  } catch (error) {
    console.log(error);
  }
};
