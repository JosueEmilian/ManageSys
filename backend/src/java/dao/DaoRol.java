package dao;

import config.ConnectionDB;
import interfaces.CrudRol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //LISTAR TODOS LOS ROLES
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

    //LISTAR ROL POR ID
    @Override
    public ModelRol list(int id) {
        try {
            strSQL = "SELECT * FROM ROL WHERE ID_ROL = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                rol.setId(rs.getInt("ID_ROL"));
                rol.setNombre(rs.getString("NOMBRE"));
                rol.setDescripcion(rs.getString("DESCRIPCION"));
                rol.setRegistrarEstado(rs.getBoolean("ESTADO"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rol;
    }

    //REGISTRAR NUEVO ROL
    @Override
    public boolean Register(ModelRol rol) {
        boolean res = false;
        try {
            strSQL = "INSERT INTO ROL (ID_ROL, NOMBRE, DESCRIPCION, ESTADO) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_ROL), 0) + 1 FROM ROL), ?, ?, ?)";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, rol.getNombre());
            pst.setString(2, rol.getDescripcion());
            pst.setBoolean(3, rol.isRegistrarEstado());

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    //ACTUALIZAR ROL
    @Override
    public boolean Update(ModelRol rol) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE ROL SET NOMBRE = ?, DESCRIPCION = ?, ESTADO = ? WHERE ID_ROL = ?";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setString(1, rol.getNombre());
            pst.setString(2, rol.getDescripcion());
            pst.setBoolean(3, rol.isRegistrarEstado());
            pst.setInt(4, rol.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    //ELIMINAR ROL POR ID
    @Override
    public boolean Delete(ModelRol rol) {
        try {
            strSQL = "DELETE ROL WHERE ID_ROL = ? ";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, rol.getId());
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (Exception e) {
            System.out.println("Error al eliminar el rol: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar rol (cerrando conexión): " + e.getMessage());
            }
        }
    }

    //BUSCAR ROL POR NOMBRE
    @Override
    public List<ModelRol> Search(String rol) {
        List<ModelRol> lst = new ArrayList<>();
        try {
            strSQL = "SELECT ID_ROL, NOMBRE, DESCRIPCION, \n"
                    + "       CASE \n"
                    + "           WHEN ESTADO = 1 THEN 'activo'\n"
                    + "           ELSE 'inactivo'\n"
                    + "       END AS ESTADO\n"
                    + "FROM ROL WHERE NOMBRE = ?";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, rol);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ModelRol nRol = new ModelRol();
                nRol.setId(rs.getInt("ID_ROL"));
                nRol.setNombre(rs.getString("NOMBRE"));
                nRol.setDescripcion(rs.getString("DESCRIPCION"));
                nRol.setEstado(rs.getString("ESTADO"));
                lst.add(nRol);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }
}
