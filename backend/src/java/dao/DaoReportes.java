package dao;

import config.ConnectionDB;
import interfaces.ReportesInterface;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelReportes;

/**
 *
 * @author josueemilian
 */
public class DaoReportes implements ReportesInterface {

    String strSQL = "";
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public List<ModelReportes> historialPedidos() {
        ArrayList<ModelReportes> lstReportes = new ArrayList<>();

        try {
            strSQL = "SELECT\n"
                    + "    PEDIDO.ID_PEDIDO,\n"
                    + "    MESA.DESCRIPCION AS Mesa,\n"
                    + "    USUARIO.NOMBRE AS Empleado,\n"
                    + "    CLIENTE.NOMBRE AS Cliente,\n"
                    + "    PEDIDO.TOTAL,\n"
                    + "    PEDIDO.OBSERVACION,\n"
                    + "    CASE PEDIDO.ESTADO\n"
                    + "        WHEN 0 THEN 'Despachado'\n"
                    + "        WHEN 1 THEN 'En Proceso'\n"
                    + "    END AS Estado,\n"
                    + "    PEDIDO.FECHA_CREA\n"
                    + "FROM PEDIDO\n"
                    + "JOIN MESA ON PEDIDO.ID_MESA = MESA.ID_MESA\n"
                    + "JOIN USUARIO ON PEDIDO.ID_EMPLEADO = USUARIO.ID_USUARIO\n"
                    + "JOIN CLIENTE ON PEDIDO.ID_CLIENTE = CLIENTE.ID_CLIENTE;";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModelReportes reporte = new ModelReportes();
                reporte.setIdPedido(rs.getInt("ID_PEDIDO"));
                reporte.setMesa(rs.getString("MESA"));
                reporte.setEmpleado(rs.getString("EMPLEADO"));
                reporte.setCliente(rs.getString("CLIENTE"));
                reporte.setTotal(rs.getDouble("TOTAL"));
                reporte.setObservacionPedido(rs.getString("OBSERVACION"));
                reporte.setEstadoPedido(rs.getString("ESTADO"));
                reporte.setFechaPedido(rs.getString("FECHA_CREA"));
                lstReportes.add(reporte);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstReportes;
    }

}
