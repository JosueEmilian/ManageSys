import xml2js from "xml-js";

export const fetchHistorialPedidos = async () => {
  try {
    const response = await fetch(
      "http://localhost:8080/backend/WSReporte?WSDL",
      {
        method: "POST",
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
        body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <historialPedidos xmlns="http://service/"/>
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
