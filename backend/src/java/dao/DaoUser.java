package dao;

import config.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Holder;
import model.User;

public class DaoUser {

    private Connection conn = null;

    public DaoUser() throws ClassNotFoundException {
        ConnectionDB connDB = new ConnectionDB();
        this.conn = connDB.open();
    }

    public void close() throws Exception {
        ConnectionDB connDB = new ConnectionDB();
        connDB.close();
    }

    //metodo para validar credenciales
public boolean validarCredenciales(String usuario, String contrasenia, Holder<List<User>> userHolder) {
    boolean resultado = false;
    List<User> userList = new ArrayList<>();
    try {
        // Crear una consulta preparada para buscar las credenciales ingresadas
        PreparedStatement ps = conn.prepareStatement("SELECT \n"
                + "  U.ID_USUARIO,\n"
                + "  U.USUARIO, \n"
                + "  U.EMAIL,\n"
                + "  R.NOMBRE AS ROL, \n"
                + "  M.NOMBRE AS MODULO, \n"
                + "  M.PATH AS RUTA\n"
                + "FROM \n"
                + "  USUARIO U \n"
                + "  INNER JOIN ROL R ON U.ID_ROL = R.ID_ROL\n"
                + "  INNER JOIN PERMISO P ON R.ID_ROL = P.ID_ROL\n"
                + "  INNER JOIN MODULO M ON P.ID_MODULO = M.ID_MODULO\n"
                + "WHERE \n"
                + "  U.EMAIL = ? AND U.[PASSWORD] = ?");
        ps.setString(1, usuario);
        ps.setString(2, contrasenia);

        // Ejecutar la consulta y obtener el resultado
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("ID_USUARIO"));
            user.setUsuario(rs.getString("USUARIO"));
            user.setEmail(rs.getString("EMAIL"));
            user.setRol(rs.getString("ROL"));
            user.setModulo(rs.getString("MODULO"));
            user.setRuta(rs.getString("RUTA"));
            userList.add(user);
        }

        if (!userList.isEmpty()) {
            resultado = true;
        }

        // Cerrar la conexión y liberar los recursos
        rs.close();
        ps.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    userHolder.value = userList;
    return resultado;
}
}
