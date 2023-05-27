import xml2js from "xml-js";

export const fetchClienteID = async (id) => {
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
            <getClienteId xmlns="http://service/">
                <id xmlns="">${id}</id>
            </getClienteId>
        </Body>
    </Envelope>`,
      }
    );
    const data = await response.text();
    const json = xml2js.xml2json(data, { compact: true, spaces: 4 });
    return JSON.parse(json); // Analizar la cadena JSON
  } catch (error) {
    console.log(error);
  }
};
