export async function SearchModuloSoap(searchTerm) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <buscarModulo xmlns="http://service/">
                        <arg0 xmlns="">${searchTerm}</arg0>
                    </buscarModulo>
                </Body>
                </Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WSCrudModulo?WSDL",
    {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    }
  );
  const data = await response.text();

  // Analizar la respuesta como un documento XML
  const parser = new DOMParser();
  const xmlDoc = parser.parseFromString(data, "text/xml");

  // Obtener el modulo de la respuesta
  const roles = Array.from(xmlDoc.getElementsByTagName("return")).map(
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

  return roles;
}
