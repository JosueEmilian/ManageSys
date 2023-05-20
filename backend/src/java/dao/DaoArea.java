package dao;

import config.ConnectionDB;
import interfaces.AreaInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelArea;

/**
 *
 * @author josueemilian
 */
public class DaoArea implements AreaInterface {

    ModelArea area = new ModelArea();
    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public List<ModelArea> listar() {
        ArrayList<ModelArea> lstArea = new ArrayList<>();
        try {
            strSQL = "SELECT ID_AREA, DESCRIPCION,\n"
                    + "       ESTADO,\n"
                    + "       CASE ESTADO\n"
                    + "           WHEN 0 THEN 'inactivo'\n"
                    + "           WHEN 1 THEN 'activo'\n"
                    + "           ELSE 'desconocido'\n"
                    + "       END AS ESTADO_DESCRIPCION\n"
                    + "FROM AREA;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelArea area = new ModelArea();
                area.setId(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getBoolean("ESTADO"));
                area.setStrEstado(rs.getString("ESTADO_DESCRIPCION"));
                lstArea.add(area);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstArea;
    }

    @Override
    public ModelArea list(int id) {
        ModelArea area = new ModelArea();

        try {
            strSQL = "SELECT * FROM AREA WHERE ID_AREA = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                area.setId(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getBoolean("ESTADO"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return area;
    }

    @Override
    public boolean Register(ModelArea area) {
        boolean res = false;
        try {
            strSQL = "INSERT INTO AREA (ID_AREA, DESCRIPCION, ESTADO) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_AREA), 0) + 1 FROM AREA), ?, ?)";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setString(1, area.getDescripcion());
            pst.setBoolean(2, area.isEstado());

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    @Override
    public boolean Update(ModelArea area) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE AREA SET DESCRIPCION = ?, ESTADO = ? WHERE ID_AREA = ?";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setString(1, area.getDescripcion());
            pst.setBoolean(2, area.isEstado());
            pst.setInt(3, area.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    @Override
    public boolean Delete(ModelArea area) {
        try {
            strSQL = "DELETE AREA WHERE ID_AREA = ? ";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setInt(1, area.getId());

            int result = pst.executeUpdate();
            return (result > 0);

        } catch (Exception e) {
            System.out.println("Error al eliminar el area: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar el area (cerrando conexión): " + e.getMessage());
            }
        }
    }

    @Override
    public List<ModelArea> Search(String area) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
