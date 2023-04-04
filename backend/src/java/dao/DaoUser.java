package dao;

import config.ConnectionDB;
import java.sql.*;
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

    public boolean validarCredenciales(String usuario, String contrasenia) {
        boolean resultado = false;
        try {
            // Crear una consulta preparada para buscar las credenciales ingresadas
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE email=? AND password=?");
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            // Verificar si las credenciales son válidas
            resultado = (count == 1);

            // Cerrar la conexión y liberar los recursos
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
}
