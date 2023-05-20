package dao;

import config.ConnectionDB;
import interfaces.MesaInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelMesa;

/**
 *
 * @author josueemilian
 */
public class DaoMesa implements MesaInterface {

    ModelMesa mesa = new ModelMesa();
    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public List<ModelMesa> listar() {
        ArrayList<ModelMesa> lstMesa = new ArrayList<>();
        try {
            strSQL = "SELECT M.ID_AREA, A.DESCRIPCION AS DESCRIPCION_AREA, M.ID_MESA, M.DESCRIPCION, M.ASIENTOS,\n"
                    + "       M.ID_ESTADO,\n"
                    + "       CASE M.ID_ESTADO\n"
                    + "           WHEN 0 THEN 'inactivo'\n"
                    + "           WHEN 1 THEN 'activo'\n"
                    + "           ELSE 'desconocido'\n"
                    + "       END AS ESTADO_DESCRIPCION\n"
                    + "FROM MESA M\n"
                    + "JOIN AREA A ON M.ID_AREA = A.ID_AREA;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelMesa mesa = new ModelMesa();
                mesa.setIdArea(rs.getInt("ID_AREA"));
                mesa.setDescArea(rs.getString("DESCRIPCION_AREA"));
                mesa.setId(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsientos(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getBoolean("ID_ESTADO"));
                mesa.setStrEstadoMesa(rs.getString("ESTADO_DESCRIPCION"));
                lstMesa.add(mesa);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMesa;
    }

    @Override
    public List<ModelMesa> list(int id) {
        ArrayList<ModelMesa> lstMesaArea = new ArrayList<>();

        try {
            strSQL = "SELECT M.ID_MESA, M.DESCRIPCION, M.ASIENTOS, M.ID_ESTADO\n"
                    + "FROM MESA M\n"
                    + "WHERE M.ID_AREA = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                ModelMesa mesa = new ModelMesa();
                mesa.setId(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsientos(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getBoolean("ID_ESTADO"));
                lstMesaArea.add(mesa);
            }

            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lstMesaArea;
    }

    @Override
    public ModelMesa listIndividual(int id) {
        ModelMesa mesa = new ModelMesa();

        try {
            strSQL = "SELECT * FROM MESA WHERE ID_MESA = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                mesa.setIdArea(rs.getInt("ID_AREA"));
                mesa.setId(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsientos(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getBoolean("ID_ESTADO"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mesa;
    }

    @Override
    public boolean Register(ModelMesa mesa) {
        boolean res = false;
        try {
            strSQL = "INSERT INTO MESA (ID_MESA, ID_AREA, DESCRIPCION, ASIENTOS, ID_ESTADO) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_MESA), 0) + 1 FROM MESA), ?, ?, ?, ?)";
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setInt(1, mesa.getIdArea());
            pst.setString(2, mesa.getDescripcion());
            pst.setInt(3, mesa.getAsientos());
            pst.setBoolean(4, mesa.isEstado());

            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            }

            pst.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    //update del estado de la mesa true or false
    @Override
    public boolean Update(ModelMesa mesa) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE MESA\n"
                    + "SET ID_ESTADO = CASE WHEN ID_ESTADO = 1 THEN 0 ELSE 1 END\n"
                    + "WHERE ID_MESA = ?;";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setInt(1, mesa.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    //update general
    @Override
    public boolean UpdateIndividual(ModelMesa mesa) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            strSQL = "UPDATE MESA SET ID_AREA = ?, DESCRIPCION = ?, ASIENTOS = ?, ID_ESTADO = ? WHERE ID_MESA = ?";
            con = conexion.open();
            pst = con.prepareStatement(strSQL);

            pst.setInt(1, mesa.getIdArea());
            pst.setString(2, mesa.getDescripcion());
            pst.setInt(3, mesa.getAsientos());
            pst.setBoolean(4, mesa.isEstado());
            pst.setInt(5, mesa.getId());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

    @Override
    public boolean Delete(ModelMesa mesa) {
        try {
            strSQL = "DELETE MESA WHERE ID_MESA = ? ";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);

            pst.setInt(1, mesa.getId());

            int result = pst.executeUpdate();
            return (result > 0);

        } catch (Exception e) {
            System.out.println("Error al eliminar la Mesa: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar la Mesa (cerrando conexión): " + e.getMessage());
            }
        }
    }

    @Override
    public List<ModelMesa> Search(String mesa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
