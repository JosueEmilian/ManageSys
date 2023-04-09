async function GetUserIDSoap(id) {
  try {
    const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:obtenerUsuarioPorId xmlns:ns2="http://service/">
            <id>${id}</id>
        </ns2:obtenerUsuarioPorId>
    </S:Body>
    </S:Envelope>`;

    const response = await fetch(
      "http://localhost:8080/backend/WSCrudUser?WSDL",
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

    // Obtener los usuarios de la respuesta
    const getUsuarioID = Array.from(xmlDoc.getElementsByTagName("return")).map(
      (node) => {
        const idNode = node.getElementsByTagName("id")[0];
        const nombreNode = node.getElementsByTagName("nombre")[0];
        const apellidoNode = node.getElementsByTagName("apellido")[0];
        const usuarioNode = node.getElementsByTagName("usuario")[0];
        const emailNode = node.getElementsByTagName("email")[0];
        const estadoNode = node.getElementsByTagName("registrarEstado")[0];
        const rolNode = node.getElementsByTagName("registrarRol")[0];
        return {
          id: idNode ? idNode.textContent : "",
          nombre: nombreNode ? nombreNode.textContent : "",
          apellido: apellidoNode ? apellidoNode.textContent : "",
          usuario: usuarioNode ? usuarioNode.textContent : "",
          email: emailNode ? emailNode.textContent : "",
          estado: estadoNode ? estadoNode.textContent : "",
          rol: rolNode ? rolNode.textContent : "",
        };
      }
    );

    return getUsuarioID;
  } catch (error) {
    console.error(error);
    throw new Error("Error al obtener los roles");
  }
}
export default GetUserIDSoap;
