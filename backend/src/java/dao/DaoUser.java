package dao;

import config.ConnectionDB;
import interfaces.CrudUser;
import java.sql.ResultSet;
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

    ModelUser usuario = new ModelUser();
    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;
    boolean res = false;

    @Override
    public List listar() {
        ArrayList<ModelUser> lstUsers = new ArrayList<>();
        try {
            strSQL = "SELECT U.ID_USUARIO, U.NOMBRE, U.APELLIDO, U.USUARIO, U.EMAIL, U.PASSWORD, R.NOMBRE AS DESC_ROL, \n"
                    + "    CASE WHEN U.ESTADO = 1 THEN 'Activo' ELSE 'Inactivo' END AS DESC_ESTADO \n"
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
                usr.setDescRol(rs.getString("DESC_ESTADO"));
                usr.setDescEstado(rs.getString("DESC_ROL"));
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

    @Override
    public ModelUser list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Register(ModelUser usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(ModelUser usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(ModelUser usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List Search(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
