import xml2js from "xml-js";

export const fetchUpdateEstadoPedido = async (id) => {
  try {
    const response = await fetch(
      "http://localhost:8080/backend/WSMonitorCocina?WSDL",
      {
        method: "POST",
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
        body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <actualizarEstadoPedido xmlns="http://service/">
                <ID_PEDIDO xmlns="">${id}</ID_PEDIDO>
            </actualizarEstadoPedido>
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
