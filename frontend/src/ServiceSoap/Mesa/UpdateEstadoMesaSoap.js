import xml2js from "xml-js";

export const fetchUpdateMesas = async (id) => {
  try {
    const response = await fetch("http://20.250.6.150:8080/backend/WSMesa?WSDL", {
      method: "POST",
      headers: {
        "Content-Type": "text/xml;charset=UTF-8",
      },
      body: `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
      <SOAP-ENV:Header/>
      <S:Body>
          <ns2:actualizarMesa xmlns:ns2="http://service/">
              <ID_MESA>${id}</ID_MESA>
          </ns2:actualizarMesa>
      </S:Body>
  </S:Envelope>`,
    });
    const data = await response.text();
    const json = xml2js.xml2js(data, { compact: true, spaces: 4 });
    return json;
  } catch (error) {
    console.log(error);
  }
};
