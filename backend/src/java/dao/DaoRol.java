package dao;

import config.ConnectionDB;
import interfaces.CrudRol;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelRol;

/**
 *
 * @author josueemilian
 */
public class DaoRol implements CrudRol {

    ModelRol rol = new ModelRol();
    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;
    boolean res = false;

    @Override
    public List<ModelRol> listar() {
        ArrayList<ModelRol> lstRol = new ArrayList<>();
        try {
            strSQL = "SELECT ID_ROL, NOMBRE, DESCRIPCION, \n"
                    + "       CASE \n"
                    + "           WHEN ESTADO = 1 THEN 'activo'\n"
                    + "           ELSE 'inactivo'\n"
                    + "       END AS ESTADO\n"
                    + "FROM ROL";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelRol rol = new ModelRol();
                rol.setId(rs.getInt("ID_ROL"));
                rol.setNombre(rs.getString("NOMBRE"));
                rol.setDescripcion(rs.getString("DESCRIPCION"));
                rol.setEstado(rs.getString("ESTADO"));
                lstRol.add(rol);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstRol;
    }

    @Override
    public ModelRol list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Register(ModelRol usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(ModelRol usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(ModelRol usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelRol> Search(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
