async function ReadRoleSoap() {
  try {
    const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:listarRoles xmlns:ns2="http://service/"/>
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

    if (!response.ok) {
      throw new Error(`Error en la petición: ${response.statusText}`);
    }

    const data = await response.text();

    // Analizar la respuesta como un documento XML
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(data, "text/xml");

    // Obtener los usuarios de la respuesta
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
  } catch (error) {
    console.error(error);
    throw new Error("Error al obtener los roles");
  }
}
export default ReadRoleSoap;

// import axios from "axios";

// async function ReadRoleSoap() {
//   const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
//   <SOAP-ENV:Header/>
//   <S:Body>
//       <ns2:listarRoles xmlns:ns2="http://service/"/>
//   </S:Body>
// </S:Envelope>`;

//   try {
//     const response = await axios.post(
//       "http://localhost:8080/backend/WSRolCrud?WSDL",
//       xml,
//       {
//         headers: { "Content-Type": "text/xml" },
//       }
//     );
//     const parser = new DOMParser();
//     const xmlDoc = parser.parseFromString(response.data, "text/xml");
//     const roles = Array.from(xmlDoc.getElementsByTagName("return")).map(
//       (node) => {
//         const idNode = node.getElementsByTagName("id")[0];
//         const nombreNode = node.getElementsByTagName("nombre")[0];
//         const descripcionNode = node.getElementsByTagName("descripcion")[0];
//         const estadoNode = node.getElementsByTagName("estado")[0];

//         return {
//           id: idNode ? idNode.textContent : "",
//           nombre: nombreNode ? nombreNode.textContent : "",
//           apellido: descripcionNode ? descripcionNode.textContent : "",
//           usuario: estadoNode ? estadoNode.textContent : "",
//         };
//       }
//     );
//     return roles;
//   } catch (error) {
//     console.error(error);
//     throw new Error("Error al obtener los roles");
//   }
// }

// export default ReadRoleSoap;
