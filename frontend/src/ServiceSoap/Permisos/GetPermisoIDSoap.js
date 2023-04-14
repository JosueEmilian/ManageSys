async function GetPermisoIDSoap(id) {
  try {
    const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                    <Body>
                        <obtenerPermisoPorID xmlns="http://service/">
                            <id xmlns="">${id}</id>
                        </obtenerPermisoPorID>
                    </Body>
                </Envelope>`;

    const response = await fetch(
      "http://localhost:8080/backend/WSCrudPermiso?WSDL",
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

    // Obtener los permisos de la respuesta
    const getPermisoID = Array.from(xmlDoc.getElementsByTagName("return")).map(
      (node) => {
        const idNode = node.getElementsByTagName("id")[0];
        const nombreModuloNode = node.getElementsByTagName("nombreModulo")[0];
        const nombreRolNode = node.getElementsByTagName("nombreRol")[0];
        return {
          id: idNode ? idNode.textContent : "",
          nombreModulo: nombreModuloNode ? nombreModuloNode.textContent : "",
          nombreRol: nombreRolNode ? nombreRolNode.textContent : "",
        };
      }
    );

    return getPermisoID;
  } catch (error) {
    console.error(error);
    throw new Error("Error al obtener el permiso");
  }
}
export default GetPermisoIDSoap;
