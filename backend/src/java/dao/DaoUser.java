package dao;

import config.ConnectionDB;
import java.sql.*;
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
public boolean validarCredenciales(String usuario, String contrasenia, Holder<User> userHolder) {
    boolean resultado = false;
    User user = null;
    try {
        // Crear una consulta preparada para buscar las credenciales ingresadas
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email=? AND password=?");
        ps.setString(1, usuario);
        ps.setString(2, contrasenia);

        // Ejecutar la consulta y obtener el resultado
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("idUsuario"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setIsAdmin(rs.getBoolean("isAdmin"));
            resultado = true;
        }

        // Cerrar la conexión y liberar los recursos
        rs.close();
        ps.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    userHolder.value = user;
    return resultado;
}
}
