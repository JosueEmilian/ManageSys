import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { login } from "../Service/userAction";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  function handleSubmit(event) {
    event.preventDefault();

    // Construir la cadena XML con los parámetros
    const xml = `<?xml version="1.0" encoding="UTF-8"?>
      <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <S:Body>
          <ns2:validarCredenciales xmlns:ns2="http://service/">
            <email>${email}</email>
            <password>${password}</password>
          </ns2:validarCredenciales>
        </S:Body>
      </S:Envelope>`;

    fetch("http://localhost:8080/backend/WSLogin", {
      method: "POST",
      headers: { "Content-Type": "text/xml" },
      body: xml,
    })
      .then((response) => response.text())
      .then((data) => {
        // Analizar la respuesta como un documento XML
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(data, "text/xml");

        // Extraer el valor del elemento de respuesta
        const resultado =
          xmlDoc.getElementsByTagName("return")[0]?.childNodes[0]?.nodeValue;

        // Verificar si los credenciales son válidos
        if (resultado === "true") {
          console.log("Éxito al iniciar sesión");
          dispatch(login(email)); // dispatch de la acción "login" con el email del usuario
          navigate("/home");
        } else {
          // Buscar mensajes de error en la respuesta
          const errors = xmlDoc.getElementsByTagName("error");
          if (errors.length > 0) {
            const message = errors[0]?.childNodes[0]?.nodeValue;
            console.log("Error al iniciar sesión:", message);
          } else {
            console.log("Error desconocido al iniciar sesión");
          }
        }
      })
      .catch((error) => {
        console.log("Error al iniciar sesión:", error);
      });
  }

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Login;
