package dao;

import config.ConnectionDB;
import interfaces.CrudUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelUser;

/**
 *
 * @author josueemilian
 */
public class DaoUser implements CrudUser {

    String strSQL = "";
    ModelUser usr = new ModelUser();
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;
    boolean res = false;

    //LISTAR USUARIOS
    @Override
    public List<ModelUser> listar() {
        ArrayList<ModelUser> lstUsers = new ArrayList<>();
        try {
            strSQL = "SELECT U.ID_USUARIO, U.NOMBRE, U.APELLIDO, U.USUARIO, U.EMAIL, U.PASSWORD, R.NOMBRE AS ROL, \n"
                    + "    CASE WHEN U.ESTADO = 1 THEN 'Activo' ELSE 'Inactivo' END AS ESTADO \n"
                    + "FROM USUARIO U\n"
                    + "INNER JOIN ROL R ON U.ID_ROL = R.ID_ROL;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelUser usr = new ModelUser();
                usr.setId(rs.getInt("ID_USUARIO"));
                usr.setNombre(rs.getString("NOMBRE"));
                usr.setApellido(rs.getString("APELLIDO"));
                usr.setUsuario(rs.getString("USUARIO"));
                usr.setEmail(rs.getString("EMAIL"));
                usr.setPassword(rs.getString("PASSWORD"));
                usr.setRol(rs.getString("ROL"));
                usr.setEstado(rs.getString("ESTADO"));
                lstUsers.add(usr);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }

    //LISTAR USUARIO POR ID
    @Override
    public ModelUser list(int id) {
        try {
            strSQL = "SELECT ID_USUARIO, NOMBRE, APELLIDO, USUARIO, EMAIL, ID_ROL, ESTADO \n"
                    + "FROM USUARIO \n"
                    + "WHERE ID_USUARIO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                usr.setId(rs.getInt("ID_USUARIO"));
                usr.setNombre(rs.getString("NOMBRE"));
                usr.setApellido(rs.getString("APELLIDO"));
                usr.setUsuario(rs.getString("USUARIO"));
                usr.setEmail(rs.getString("EMAIL"));
                usr.setRegistrarRol(rs.getInt("ID_ROL"));
                usr.setRegistrarEstado(rs.getBoolean("ESTADO"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usr;
    }

    //REGISTRAR USUARIO
    @Override
    public boolean Register(ModelUser usuario) {
        boolean res = false;
        String strSQL = "INSERT INTO USUARIO (ID_USUARIO, NOMBRE, APELLIDO, USUARIO, EMAIL, PASSWORD, ID_ROL, ESTADO) "
                + "VALUES ((SELECT ISNULL(MAX(ID_USUARIO), 0) + 1 FROM USUARIO), ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getUsuario());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getPassword());
            pst.setInt(6, usuario.getRegistrarRol());
            pst.setBoolean(7, usuario.isRegistrarEstado());

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    @Override
    public boolean Update(ModelUser usuario) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conexion.open();

            // Se prepara la consulta SQL
            String sql = "UPDATE USUARIO SET NOMBRE = ?, APELLIDO = ?, USUARIO = ?, EMAIL = ?, PASSWORD = ?, ID_ROL = ?, ESTADO = ? WHERE ID_USUARIO = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getUsuario());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getPassword());
            pst.setInt(6, usuario.getRegistrarRol());
            pst.setBoolean(7, usuario.isRegistrarEstado());
            pst.setInt(8, usuario.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();    
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    @Override
    public boolean Delete(ModelUser usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelUser> Search(String usuario) {
        List<ModelUser> lst = new ArrayList<>();
        try {
            String strSQL = "SELECT U.ID_USUARIO, U.NOMBRE, U.APELLIDO, U.USUARIO, U.EMAIL, R.NOMBRE AS ROL, "
                    + "CASE WHEN U.ESTADO = 1 THEN 'Activo' ELSE 'Inactivo' END AS ESTADO "
                    + "FROM USUARIO U "
                    + "INNER JOIN ROL R ON U.ID_ROL = R.ID_ROL "
                    + "WHERE U.USUARIO = ? OR U.EMAIL = ?";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, usuario);
            pst.setString(2, usuario);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ModelUser usr = new ModelUser();
                usr.setId(rs.getInt("ID_USUARIO"));
                usr.setNombre(rs.getString("NOMBRE"));
                usr.setApellido(rs.getString("APELLIDO"));
                usr.setUsuario(rs.getString("USUARIO"));
                usr.setEmail(rs.getString("EMAIL"));
                usr.setRol(rs.getString("ROL"));
                usr.setEstado(rs.getString("ESTADO"));
                lst.add(usr);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

}
