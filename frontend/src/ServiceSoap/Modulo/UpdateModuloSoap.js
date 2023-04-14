export async function UpdateModuloSoap(nombre, path, nivel, idPadre, id) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <actualizarModulo xmlns="http://service/">
                        <NOMBRE xmlns="">${nombre}</NOMBRE>
                        <PATH xmlns="">${path}</PATH>
                        <NIVEL xmlns="">${nivel}</NIVEL>
                        <ID_MODULO_PADRE xmlns="">${idPadre}</ID_MODULO_PADRE>
                        <ID_MODULO xmlns="">${id}</ID_MODULO>
                    </actualizarModulo>
                </Body>
                </Envelope>`;

  const response = await fetch(
    "http://localhost:8080/backend/WSCrudModulo?WSDL",
    {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    }
  );
}
