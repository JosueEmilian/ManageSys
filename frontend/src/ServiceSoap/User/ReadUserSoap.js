export async function ReadUserSoap() {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
      <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
          <ns2:listarUsuarios xmlns:ns2="http://service/"/>
        </S:Body>
      </S:Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WSCrudUser?WSDL",
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

  // Obtener los usuarios de la respuesta
  const users = Array.from(xmlDoc.getElementsByTagName("return")).map(
    (node) => {
      const idNode = node.getElementsByTagName("id")[0];
      const nombreNode = node.getElementsByTagName("nombre")[0];
      const apellidoNode = node.getElementsByTagName("apellido")[0];
      const usuarioNode = node.getElementsByTagName("usuario")[0];
      const emailNode = node.getElementsByTagName("email")[0];
      const rolNode = node.getElementsByTagName("rol")[0];
      const estadoNode = node.getElementsByTagName("estado")[0];

      return {
        id: idNode ? idNode.textContent : "",
        nombre: nombreNode ? nombreNode.textContent : "",
        apellido: apellidoNode ? apellidoNode.textContent : "",
        usuario: usuarioNode ? usuarioNode.textContent : "",
        email: emailNode ? emailNode.textContent : "",
        rol: rolNode ? rolNode.textContent : "",
        estado: estadoNode ? estadoNode.textContent : "",
      };
    }
  );

  return users;
}
