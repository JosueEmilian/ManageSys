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
            strSQL = "SELECT * FROM MESA";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelMesa mesa = new ModelMesa();
                mesa.setIdArea(rs.getInt("ID_AREA"));
                mesa.setId(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsientos(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getBoolean("ID_ESTADO"));
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
    public boolean Register(ModelMesa mesa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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

    @Override
    public boolean Delete(ModelMesa mesa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelMesa> Search(String mesa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
