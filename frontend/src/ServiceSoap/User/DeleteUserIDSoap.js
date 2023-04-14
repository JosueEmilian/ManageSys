export async function DeleteUserId(id) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <S:Body>
      <ns2:EliminarUsuarios xmlns:ns2="http://service/">
          <ID>${id}</ID>
      </ns2:EliminarUsuarios>
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
  if (response.ok) {
    const data = await response.text();
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(data, "text/xml");
    const result =
      xmlDoc.getElementsByTagName("return")[0].childNodes[0].nodeValue;
    return result === "true";
  } else {
    throw new Error("No se pudo eliminar el usuario.");
  }
}
