import axios from "axios";
import { xml2js } from "xml-js";

export const fetchMesas = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/backend/WSMesa?WSDL",
      `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
         <Body>
           <listarMesas xmlns="http://service/"/>
         </Body>
       </Envelope>`,
      {
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
        },
      }
    );

    const data = response.data;
    const json = xml2js(data, { compact: true, spaces: 4 });
    return json;
  } catch (error) {
    console.log(error);
  }
};
