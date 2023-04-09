export async function UpdateUserSoap(
  nombre,
  apellido,
  usuario,
  email,
  password,
  rol,
  estado,
  id
) {
  const xml = `<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
            <SOAP-ENV:Header/>
            <S:Body>
                <ns2:actualizarUsuario xmlns:ns2="http://service/">
                    <nombre>${nombre}</nombre>
                    <apellido>${apellido}</apellido>
                    <usuario>${usuario}</usuario>
                    <email>${email}</email>
                    <password>${password}</password>
                    <idRol>${rol}</idRol>
                    <estado>${estado}</estado>
                    <id>${id}</id>
                </ns2:actualizarUsuario>
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
}
