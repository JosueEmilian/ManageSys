export async function UpdateRolSoap(nombre, descripcion, estado, id) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
            <ns2:actualizarRol xmlns:ns2="http://service/">
                <NOMBRE>${nombre}</NOMBRE>
                <DESCRIPCION>${descripcion}</DESCRIPCION>
                <ESTADO>${estado}</ESTADO>
                <ID_ROL>${id}</ID_ROL>
            </ns2:actualizarRol>
        </S:Body>
        </S:Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WebServiceRol?WSDL",
    {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    }
  );
}
