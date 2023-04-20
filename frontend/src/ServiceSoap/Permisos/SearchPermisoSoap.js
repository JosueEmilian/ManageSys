export async function SearchPermisoSoap(searchTerm) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
          <Body>
              <buscarPermiso xmlns="http://service/">
                  <arg0 xmlns="">${searchTerm}</arg0>
              </buscarPermiso>
          </Body>
        </Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WSCrudPermiso?WSDL",
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

  // Obtener el permiso de la respuesta
  const permisos = Array.from(xmlDoc.getElementsByTagName("return")).map(
    (node) => {
      const idNode = node.getElementsByTagName("id")[0];
      const nombreModuloNode = node.getElementsByTagName("nombreModulo")[0];
      const pathNode = node.getElementsByTagName("pathModulo")[0];
      const nombreRolNode = node.getElementsByTagName("nombreRol")[0];
      return {
        id: idNode ? idNode.textContent : "",
        nombreModulo: nombreModuloNode ? nombreModuloNode.textContent : "",
        pathModulo: pathNode ? pathNode.textContent : "",
        nombreRol: nombreRolNode ? nombreRolNode.textContent : "",
      };
    }
  );

  return permisos;
}
