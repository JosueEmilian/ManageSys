import xml2js from "xml-js";

export const fetchRegisterProductoNuevo = async (
  idTipo,
  descripcion,
  precio,
  imagen
) => {
  try {
    const response = await fetch(
      "http://localhost:8080/backend/WSProductoNuevo?WSDL",
      {
        method: "POST",
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
        body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <insertarProductoNuevo xmlns="http://service/">
                <idTipo xmlns="">${idTipo}</idTipo>
                <descripcion xmlns="">${descripcion}</descripcion>
                <precio xmlns="">${precio}</precio>
                <imagen xmlns="">${imagen}</imagen>
            </insertarProductoNuevo>
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