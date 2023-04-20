export async function RegisterUserSoap(
  nombre,
  apellido,
  usuario,
  email,
  password,
  rol,
  estado
) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
            <ns2:registrarUsuario xmlns:ns2="http://service/">
                <Nombre>${nombre}</Nombre>
                <Apellido>${apellido}</Apellido>
                <Usuario>${usuario}</Usuario>
                <Email>${email}</Email>
                <Password>${password}</Password>
                <id_Rol>${rol}</id_Rol>
                <id_Estado>${estado}</id_Estado>
            </ns2:registrarUsuario>
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
}
