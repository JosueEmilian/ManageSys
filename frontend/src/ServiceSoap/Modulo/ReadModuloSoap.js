async function ReadModuloSoap() {
  try {
    const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                    <Body>
                        <listarTodosLosModulos xmlns="http://service/"/>
                    </Body>
                </Envelope>`;

    const response = await fetch(
      "http://localhost:8080/backend/WSCrudModulo?WSDL",
      {
        method: "POST",
        headers: { "Content-Type": "text/xml" },
        body: xml,
      }
    );

    if (!response.ok) {
      throw new Error(`Error en la petición: ${response.statusText}`);
    }

    const data = await response.text();

    // Analizar la respuesta como un documento XML
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(data, "text/xml");

    // Obtener los modulos de la respuesta
    const modulos = Array.from(xmlDoc.getElementsByTagName("return")).map(
      (node) => {
        const idNode = node.getElementsByTagName("id")[0];
        const nombreNode = node.getElementsByTagName("nombre")[0];
        const pathNode = node.getElementsByTagName("path")[0];
        const nivelNode = node.getElementsByTagName("nivel")[0];
        const idModuloPadreNode = node.getElementsByTagName("idModuloPadre")[0];

        return {
          id: idNode ? idNode.textContent : "",
          nombre: nombreNode ? nombreNode.textContent : "",
          path: pathNode ? pathNode.textContent : "",
          nivel: nivelNode ? nivelNode.textContent : "",
          idModuloPadre: idModuloPadreNode ? idModuloPadreNode.textContent : "",
        };
      }
    );

    return modulos;
  } catch (error) {
    console.error(error);
    throw new Error("Error al obtener los modulos");
  }
}
export default ReadModuloSoap;
