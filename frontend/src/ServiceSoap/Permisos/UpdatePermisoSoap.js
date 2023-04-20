export async function UpdatePermisoSoap(idModulo, idRol, id) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <actualizarPermisos xmlns="http://service/">
                        <ID_MODULO xmlns="">${idModulo}</ID_MODULO>
                        <ID_ROL xmlns="">${idRol}</ID_ROL>
                        <ID_PERMISO xmlns="">${id}</ID_PERMISO>
                    </actualizarPermisos>
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
