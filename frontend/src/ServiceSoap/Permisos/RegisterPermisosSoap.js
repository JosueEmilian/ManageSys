export async function RegistrarPermisoSoap(idModulo, idRol) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <registrarPermiso xmlns="http://service/">
                        <ID_MODULO xmlns="">${idModulo}</ID_MODULO>
                        <ID_ROL xmlns="">${idRol}</ID_ROL>
                    </registrarPermiso>
                </Body>
                </Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WSCrudPermiso?WSDL",
    {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    }
  );
}
