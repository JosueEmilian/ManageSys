export async function SearchRolSoap(searchTerm) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
            <ns2:buscarRol xmlns:ns2="http://service/">
                <arg0>${searchTerm}</arg0>
            </ns2:buscarRol>
        </S:Body>
        </S:Envelope>`;

  const response = await fetch(
    "http://localhost:8080/backend/WebServiceRol?WSDL",
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

  // Obtener los roles de la respuesta
  const roles = Array.from(xmlDoc.getElementsByTagName("return")).map(
    (node) => {
      const idNode = node.getElementsByTagName("id")[0];
      const nombreNode = node.getElementsByTagName("nombre")[0];
      const descripcionNode = node.getElementsByTagName("descripcion")[0];
      const estadoNode = node.getElementsByTagName("estado")[0];

      return {
        id: idNode ? idNode.textContent : "",
        nombre: nombreNode ? nombreNode.textContent : "",
        descripcion: descripcionNode ? descripcionNode.textContent : "",
        estado: estadoNode ? estadoNode.textContent : "",
      };
    }
  );

  return roles;
}
