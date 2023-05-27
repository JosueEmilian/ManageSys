package dao;

import config.ConnectionDB;
import interfaces.MonitorCocinaInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ModelMonitorCocina;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josueemilian
 */
public class DaoMonitorCocina implements MonitorCocinaInterface {

    ModelMonitorCocina monitorCocina = new ModelMonitorCocina();
    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public List<ModelMonitorCocina> listar() {
        ArrayList<ModelMonitorCocina> lstMonitor = new ArrayList<>();
        try {
            strSQL = "SELECT p.ID_PEDIDO, p.OBSERVACION, pc.DESCRIPCION, pc.IMAGEN, dp.CANTIDAD, m.DESCRIPCION AS DESCRIPCION_MESA, a.DESCRIPCION AS DESCRIPCION_AREA,\n"
                    + "       CONVERT(VARCHAR(8), p.FECHA_CREA, 108) AS HORA_PEDIDO\n"
                    + "FROM PEDIDO p\n"
                    + "JOIN DETALLE_PEDIDO dp ON p.ID_PEDIDO = dp.ID_PEDIDO\n"
                    + "JOIN PRODUCTO_COMBO pc ON dp.ID_COMBO = pc.ID_COMBO\n"
                    + "JOIN MESA m ON p.ID_MESA = m.ID_MESA\n"
                    + "JOIN AREA a ON m.ID_AREA = a.ID_AREA\n"
                    + "WHERE p.ESTADO = 1";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelMonitorCocina monitorCocina = new ModelMonitorCocina();
                monitorCocina.setIdPedido(rs.getInt("ID_PEDIDO"));
                monitorCocina.setObservacion(rs.getString("OBSERVACION"));
                monitorCocina.setDescripcion(rs.getString("DESCRIPCION"));
                monitorCocina.setImagen(rs.getString("IMAGEN"));
                monitorCocina.setCantidad(rs.getInt("CANTIDAD"));
                monitorCocina.setMesa(rs.getString("DESCRIPCION_MESA"));
                monitorCocina.setArea(rs.getString("DESCRIPCION_AREA"));
                monitorCocina.setHora(rs.getString("HORA_PEDIDO"));

                lstMonitor.add(monitorCocina);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMonitorCocina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoMonitorCocina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMonitor;
    }

    @Override
    public boolean Update(ModelMonitorCocina monitorCocina) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pstPedido = null;
        PreparedStatement pstMesa = null;
        try {
            con = conexion.open();
            con.setAutoCommit(false);

            // Actualizar el estado del pedido
            String strSQLPedido = "UPDATE PEDIDO SET ESTADO = 0 WHERE ID_PEDIDO = ?;";
            pstPedido = con.prepareStatement(strSQLPedido);
            pstPedido.setInt(1, monitorCocina.getIdPedido());
            int resultadoPedido = pstPedido.executeUpdate();

            // obtenemos el ID de la mesa
            int idMesa = 0;
            if (resultadoPedido > 0) {
                String strSQLMesa = "SELECT ID_MESA FROM PEDIDO WHERE ID_PEDIDO = ?;";
                pstMesa = con.prepareStatement(strSQLMesa);
                pstMesa.setInt(1, monitorCocina.getIdPedido());
                ResultSet rsMesa = pstMesa.executeQuery();
                if (rsMesa.next()) {
                    idMesa = rsMesa.getInt("ID_MESA");
                }
            }

            // actualizmos el estado de la mesa
            if (idMesa > 0) {
                String strSQLUpdateMesa = "UPDATE MESA SET ID_ESTADO = 1 WHERE ID_MESA = ?;";
                PreparedStatement pstUpdateMesa = con.prepareStatement(strSQLUpdateMesa);
                pstUpdateMesa.setInt(1, idMesa);
                int resultadoUpdateMesa = pstUpdateMesa.executeUpdate();

                if (resultadoUpdateMesa > 0) {
                    cambioExitoso = true;
                }
            }

            if (cambioExitoso) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMonitorCocina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoMonitorCocina.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstPedido != null) {
                    pstPedido.close();
                }
                if (pstMesa != null) {
                    pstMesa.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoMonitorCocina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }

}
