import xml2js from "xml-js";

export const fetchUpdateMesa = async (
  idArea,
  descripcion,
  asientos,
  estado,
  id
) => {
  try {
    const response = await fetch("http://localhost:8080/backend/WSMesa?WSDL", {
      method: "POST",
      headers: {
        "Content-Type": "text/xml;charset=UTF-8",
      },
      body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <actualizarMesasGeneral xmlns="http://service/">
              <ID_AREA xmlns="">${idArea}</ID_AREA>
              <DESCRIPCION xmlns="">${descripcion}</DESCRIPCION>
              <ASIENTOS xmlns="">${asientos}</ASIENTOS>
              <ID_ESTADO xmlns="">${estado}</ID_ESTADO>
              <ID_MESA xmlns="">${id}</ID_MESA>
          </actualizarMesasGeneral>
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
