import xml2js from "xml-js";

export const fetchRegisterArea = async (descripcion, estado) => {
  try {
    const response = await fetch("http://localhost:8080/backend/WSArea?WSDL", {
      method: "POST",
      headers: {
        "Content-Type": "text/xml;charset=UTF-8",
      },
      body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <registrarArea xmlns="http://service/">
              <DESCRIPCION xmlns="">${descripcion}</DESCRIPCION>
              <ESTADO xmlns="">${estado}</ESTADO>
          </registrarArea>
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
