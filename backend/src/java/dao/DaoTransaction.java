package dao;

import config.ConnectionDB;
import interfaces.TransactionInterface;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelTransaction;

/**
 *
 * @author josueemilian
 */
public class DaoTransaction implements TransactionInterface {

    ModelTransaction transaction = new ModelTransaction();
    ConnectionDB conexion = new ConnectionDB();
    ResultSet rs = null;

    @Override
    public boolean Register(ModelTransaction transaction) {
        boolean res = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = conexion.open();
            con.setAutoCommit(false);  // Desactivamos el modo de confirmación automática

            // Obtenemos el ID_CLIENTE máximo actual
            String selectMaxClientIdQuery = "SELECT ISNULL(MAX(ID_CLIENTE), 0) FROM CLIENTE";
            pst = con.prepareStatement(selectMaxClientIdQuery);
            rs = pst.executeQuery();
            int maxClientId = 0;
            if (rs.next()) {
                maxClientId = rs.getInt(1);
            }
            pst.close();

            // Generamos el nuevo ID_CLIENTE sumando 1 al máximo actual
            int generatedClientId = maxClientId + 1;

            // Insertamos el cliente en la tabla CLIENTE
            String insertClienteQuery = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE, NIT, RAZON_SOCIAL, NICKNAME, DIRECCION, TELEFONO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(insertClienteQuery);
            pst.setInt(1, generatedClientId);
            pst.setString(2, transaction.getNombreCliente());
            pst.setString(3, transaction.getNitCliente());
            pst.setString(4, transaction.getRazonSocialCliente());
            pst.setString(5, transaction.getNicknameCliente());
            pst.setString(6, transaction.getDireccionCliente());
            pst.setString(7, transaction.getTelefonoCliente());
            pst.executeUpdate();
            pst.close();

            System.out.println("El id del cliente es: " + generatedClientId);

            
            // Obtenemos el ID_PEDIDO máximo actual
            String selectMaxPedidoIdQuery = "SELECT ISNULL(MAX(ID_PEDIDO), 0) FROM PEDIDO";
            pst = con.prepareStatement(selectMaxPedidoIdQuery);
            rs = pst.executeQuery();
            int maxPedidoId = 0;
            if (rs.next()) {
                maxPedidoId = rs.getInt(1);
            }
            pst.close();

            // Generamos el nuevo ID_CLIENTE sumando 1 al máximo actual
            int generatedPedidoId = maxPedidoId + 1;

            System.out.println("El id_Pedido es: " + generatedPedidoId);

            // Insertamos el registro en la tabla PEDIDO
            String insertPedidoQuery = "INSERT INTO PEDIDO (ID_PEDIDO, ID_MESA, ID_EMPLEADO, ID_CLIENTE, OBSERVACION, ESTADO, FECHA_CREA) "
                    + "VALUES (?, ?, ?, ?, ?, 1, GETDATE())";

            pst = con.prepareStatement(insertPedidoQuery, Statement.RETURN_GENERATED_KEYS);

            int mesaId = transaction.getIdMesa(); // Almacenamos el valor de transaction.getIdMesa(), para reutilizarla

            pst.setInt(1, generatedPedidoId);
            pst.setInt(2, mesaId);
            pst.setInt(3, transaction.getIdEmpleado());
            pst.setInt(4, generatedClientId); // Usamos el ID_CLIENTE generado automáticamente
            pst.setString(5, transaction.getObservacionPedido());
            pst.executeUpdate();

            // Insertar los registros en la tabla DETALLE_PEDIDO
            String insertDetallePedidoQuery = "INSERT INTO DETALLE_PEDIDO (ID_DETALLE_PEDIDO, ID_PEDIDO, ID_COMBO, CANTIDAD, PRECIO, TOTAL_LINEA, OBSERVACION, ESTADO, FECHA_CREA) "
                    + "VALUES ((SELECT ISNULL(MAX(ID_DETALLE_PEDIDO), 0) + 1 FROM DETALLE_PEDIDO), ?, ?, ?, ?, ?, ?, 1, GETDATE())";
            pst = con.prepareStatement(insertDetallePedidoQuery);
            pst.setInt(1, generatedPedidoId); //Usamos el ID_PEDIDO generado automáticamente
            pst.setInt(2, transaction.getIdCombo());
            pst.setInt(3, transaction.getCantidadDetalle());
            pst.setDouble(4, transaction.getPrecioDetalle());
            pst.setDouble(5, transaction.getTotalLineaDetalle());
            pst.setString(6, transaction.getObservacionDetalle());
            pst.executeUpdate();
            pst.close();

            // Acutalizamos el campo TOTAL en la tabla PEDIDO
            String updatePedidoQuery = "UPDATE PEDIDO SET TOTAL = (SELECT SUM(TOTAL_LINEA) FROM DETALLE_PEDIDO WHERE ID_PEDIDO = ?) "
                    + "WHERE ID_PEDIDO = ?";
            pst = con.prepareStatement(updatePedidoQuery);
            pst.setInt(1, generatedPedidoId);
            pst.setInt(2, generatedPedidoId);
            pst.executeUpdate();
            pst.close();

            // Actualizamos el estado de la mesa a "no disponible"
            String updateMesaQuery = "UPDATE MESA SET ID_ESTADO = 0 WHERE ID_MESA = ?";
            pst = con.prepareStatement(updateMesaQuery);
            pst.setInt(1, mesaId); //mismo id mesa
            pst.executeUpdate();
            pst.close();

            con.commit();  // Confirmamos la transacción
            res = true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoTransaction.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
            if (con != null) {
                try {
                    con.rollback();  // Revertimos la transacción en caso de error
                } catch (SQLException e) {
                    Logger.getLogger(DaoTransaction.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(DaoTransaction.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    Logger.getLogger(DaoTransaction.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(DaoTransaction.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return res;
    }

}
