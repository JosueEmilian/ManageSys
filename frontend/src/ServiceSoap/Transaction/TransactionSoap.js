import xml2js from "xml-js";

export const fetchTransaction = async (
  nombreCliente,
  nitCliente,
  razonSocialCliente,
  nickNameCliente,
  direccionCliente,
  telefonoCliente,
  idMesa,
  idEmpleado,
  observacionPedido,
  idCombo,
  cantidadDetalle,
  precioDetalle,
  totalDetalle,
  observacionDetalle
) => {
  try {
    const response = await fetch(
      "http://localhost:8080/backend/WSTransaction?WSDL",
      {
        method: "POST",
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
        body: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        <Body>
            <registerTransaction xmlns="http://service/">
                <nombreCliente xmlns="">${nombreCliente}</nombreCliente>
                <nitCliente xmlns="">${nitCliente}</nitCliente>
                <razonSocialCliente xmlns="">${razonSocialCliente}</razonSocialCliente>
                <nicknameCliente xmlns="">${nickNameCliente}</nicknameCliente>
                <direccionCliente xmlns="">${direccionCliente}</direccionCliente>
                <telefonoCliente xmlns="">${telefonoCliente}</telefonoCliente>
                <idMesa xmlns="">${idMesa}</idMesa>
                <idEmpleado xmlns="">${idEmpleado}</idEmpleado>
                <observacionPedido xmlns="">${observacionPedido}</observacionPedido>
                <idCombo xmlns="">${idCombo}</idCombo>
                <cantidadDetalle xmlns="">${cantidadDetalle}</cantidadDetalle>
                <precioDetalle xmlns="">${precioDetalle}</precioDetalle>
                <totalLineaDetalle xmlns="">${totalDetalle}</totalLineaDetalle>
                <observacionDetalle xmlns="">${observacionDetalle}</observacionDetalle>
            </registerTransaction>
        </Body>
    </Envelope>`,
      }
    );
    const data = await response.text();
    const json = xml2js.xml2js(data, { compact: true, spaces: 4 });
    return json;
  } catch (error) {
    console.log(error);
  }
};
