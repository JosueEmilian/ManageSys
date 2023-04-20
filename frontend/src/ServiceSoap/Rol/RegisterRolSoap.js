export async function RegisterRolSoap(nombre, descripcion, estado) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
            <ns2:registrarRol xmlns:ns2="http://service/">
                <Nombre>${nombre}</Nombre>
                <Descripcion>${descripcion}</Descripcion>
                <Estado>${estado}</Estado>
            </ns2:registrarRol>
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
