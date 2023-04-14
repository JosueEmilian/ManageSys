package dao;

import config.ConnectionDB;
import interfaces.CrudPermiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelPermiso;

/**
 *
 * @author josueemilian
 */
public class DaoPermiso implements CrudPermiso {

    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;
    String strSQL = "";

    //LISTAR TODOS LOS PERMISOS
    @Override
    public List<ModelPermiso> listar() {
        ArrayList<ModelPermiso> lstPermiso = new ArrayList<>();
        try {
            strSQL = "SELECT PERMISO.ID_PERMISO, MODULO.NOMBRE AS NOMBRE_MODULO, MODULO.PATH, ROL.NOMBRE AS NOMBRE_ROL\n"
                    + "FROM PERMISO\n"
                    + "INNER JOIN MODULO ON PERMISO.ID_MODULO = MODULO.ID_MODULO\n"
                    + "INNER JOIN ROL ON PERMISO.ID_ROL = ROL.ID_ROL;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelPermiso permiso = new ModelPermiso();
                permiso.setId(rs.getInt("ID_PERMISO"));
                permiso.setNombreModulo(rs.getString("NOMBRE_MODULO"));
                permiso.setPathModulo(rs.getString("PATH"));
                permiso.setNombreRol(rs.getString("NOMBRE_ROL"));
                lstPermiso.add(permiso);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPermiso;
    }

    //LISTAR PERMISO POR ID
    @Override
    public ModelPermiso list(int id) {
        ModelPermiso permiso = new ModelPermiso();

        try {
            strSQL = "SELECT P.ID_PERMISO, M.NOMBRE AS NOMBRE_MODULO, R.NOMBRE AS NOMBRE_ROL\n"
                    + "FROM PERMISO P\n"
                    + "JOIN MODULO M ON P.ID_MODULO = M.ID_MODULO\n"
                    + "JOIN ROL R ON P.ID_ROL = R.ID_ROL\n"
                    + "WHERE P.ID_PERMISO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                permiso.setId(rs.getInt("ID_PERMISO"));
                permiso.setNombreModulo(rs.getString("NOMBRE_MODULO"));
                permiso.setNombreRol(rs.getString("NOMBRE_ROL"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return permiso;
    }

    //AGREGAR NUEVOS PERMISOS
    @Override
    public boolean Register(ModelPermiso permiso) {
        boolean res = false;
        try {
            strSQL = "INSERT INTO PERMISO (ID_PERMISO, ID_MODULO, ID_ROL) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_PERMISO), 0) + 1 FROM PERMISO), ?, ?)";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setInt(1, permiso.getIdModulo());
            pst.setInt(2, permiso.getIdRol());

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    //MODIFICAR PERMISOS
    @Override
    public boolean Update(ModelPermiso permiso) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE PERMISO SET ID_MODULO = ?, ID_ROL = ? WHERE ID_PERMISO = ?";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setInt(1, permiso.getIdModulo());
            pst.setInt(2, permiso.getIdRol());
            pst.setInt(3, permiso.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    //ELIMINAR PERMISOS POR ID
    @Override
    public boolean Delete(ModelPermiso permiso) {
        try {
            strSQL = "DELETE PERMISO WHERE ID_PERMISO = ? ";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setInt(1, permiso.getId());

            int result = pst.executeUpdate();
            return (result > 0);

        } catch (Exception e) {
            System.out.println("Error al eliminar el permiso: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar el permiso (cerrando conexión): " + e.getMessage());
            }
        }
    }

    //BUSQUEDA DE PERMISOS POR NOMBRE MODULO O NOMBRE ROL
    @Override
    public List<ModelPermiso> Search(String permiso) {
        List<ModelPermiso> lst = new ArrayList<>();
        try {
            strSQL = "SELECT PERMISO.ID_PERMISO, MODULO.NOMBRE AS NOMBRE_MODULO, MODULO.PATH, ROL.NOMBRE AS NOMBRE_ROL\n"
                    + "FROM PERMISO\n"
                    + "INNER JOIN MODULO ON PERMISO.ID_MODULO = MODULO.ID_MODULO\n"
                    + "INNER JOIN ROL ON PERMISO.ID_ROL = ROL.ID_ROL\n"
                    + "WHERE MODULO.NOMBRE = ? OR ROL.NOMBRE = ?";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, permiso);
            pst.setString(2, permiso);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ModelPermiso nPermiso = new ModelPermiso();
                nPermiso.setId(rs.getInt("ID_PERMISO"));
                nPermiso.setNombreModulo(rs.getString("NOMBRE_MODULO"));
                nPermiso.setPathModulo(rs.getString("PATH"));
                nPermiso.setNombreRol(rs.getString("NOMBRE_ROL"));
                lst.add(nPermiso);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                Logger.getLogger(DaoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

}
