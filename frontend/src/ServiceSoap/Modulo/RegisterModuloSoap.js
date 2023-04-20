export async function RegisterModuloSoap(nombre, path, nivel, idPadre) {
  const xml = `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <registrarModulo xmlns="http://service/">
                        <NOMBRE xmlns="">${nombre}</NOMBRE>
                        <PATH xmlns="">${path}</PATH>
                        <NIVEL xmlns="">${nivel}</NIVEL>
                        <ID_MODULO_PADRE xmlns="">${idPadre}</ID_MODULO_PADRE>
                    </registrarModulo>
                </Body>
                </Envelope>`;

  const response = await fetch(
    "http://20.250.6.150:8080/backend/WSCrudModulo?WSDL",
    {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    }
  );
}
