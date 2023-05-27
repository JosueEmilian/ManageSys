/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConnectionDB;
import interfaces.PedidoInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModeloPedido;

/**
 *
 * @author abner
 */
public class DaoPedido implements PedidoInterface{
    
    ConnectionDB conexion = new ConnectionDB();
    ModeloPedido pedido = new ModeloPedido();
    String strSQL = "";
    ResultSet rs = null;


    @Override
    public List<ModeloPedido> pedidos() {
        ArrayList<ModeloPedido> pedidos = new ArrayList<>();
        try {
            strSQL = "select * from pedido where estado <> 0 ";
            conexion.open();
            rs = conexion.executeQuery(strSQL);

            while (rs.next()) {
                ModeloPedido pedido = new ModeloPedido();
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdMesa(rs.getInt("ID_MESA"));
                pedido.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                pedido.setIdCliente(rs.getInt("ID_CLIENTE"));
                pedido.setTotal(rs.getInt("TOTAL"));
                pedido.setObservaciones(rs.getString("OBSERVACION"));
                pedido.setEstado(rs.getInt("ESTADO"));
                pedido.setFechaCreacion(rs.getString("FECHA_CREA"));
                pedidos.add(pedido);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }
     
    @Override
    public ModeloPedido infoPedido(int id) {
        try {
            strSQL = "SELECT * \n"
                    + "FROM PEDIDO \n"
                    + "WHERE ID_PEDIDO = ?";

            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                pedido.setIdPedido(id);
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdMesa(rs.getInt("ID_MESA"));
                pedido.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                pedido.setIdCliente(rs.getInt("ID_CLIENTE"));
                pedido.setTotal(rs.getInt("TOTAL"));
                pedido.setObservaciones(rs.getString("OBSERVACION"));
                pedido.setEstado(rs.getInt("ESTADO"));
                pedido.setFechaCreacion(rs.getString("FECHA_CREA"));
            }
            rs.close();
            pst.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return pedido;
    }

    @Override
    public boolean insertarPedido(ModeloPedido pedido) {
        boolean res = false;
        strSQL = "INSERT INTO pedido (ID_PEDIDO, ID_MESA, ID_EMPLEADO, ID_CLIENTE, TOTAL, OBSERVACION, ESTADO, FECHA_CREA) "
                + "VALUES ((SELECT ISNULL(MAX(ID_PEDIDO), 0) + 1 FROM PEDIDO), ?, ?, ?, ?, ?, ?, getdate())";

        try {
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL); 
            pst.setInt(1, pedido.getIdMesa());
            pst.setInt(2, pedido.getIdEmpleado());
            pst.setInt(3, pedido.getIdCliente());
            pst.setDouble(4, pedido.getTotal());
            pst.setString(5, pedido.getObservaciones());
            pst.setInt(6, pedido.getEstado());
            
            int result = pst.executeUpdate();

            if (result > 0) {
                res = true;
            } 
            pst.close();
            con.close(); 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        } 
        return res;
    }

    @Override
    public boolean modificarPedido(ModeloPedido pedido) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conexion.open();

            // Se prepara la consulta SQL
                String sql = "UPDATE PEDIDO SET ID_MESA = ?, ID_EMPLEADO = ?, ID_CLIENTE = ?, TOTAL = ?, OBSERVACION = ?, ESTADO = ?  WHERE ID_PEDIDO = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, pedido.getIdMesa());
            pst.setInt(2, pedido.getIdEmpleado());
            pst.setInt(3, pedido.getIdCliente());
            pst.setDouble(4, pedido.getTotal());
            pst.setString(5, pedido.getObservaciones());
            pst.setInt(6, pedido.getEstado());
            pst.setInt(7, pedido.getIdPedido()); 

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                cambioExitoso = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cambioExitoso;
    }
    
    
    private void gaurdarPrueba(String sql){
        try {
            File file = new File("log.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sql);
            bw.close();
        } catch (Exception e) {
        }
    }
    

    @Override
    public boolean eliminarPedido(int idPedido) {
        try {
            strSQL = "UPDATE  PEDIDO SET ESTADO = 0 WHERE ID_PEDIDO = ? ";
 
            Connection con = conexion.open();
            PreparedStatement pst = con.prepareStatement(strSQL);
            pst.setInt(1, idPedido);
            
            
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (Exception e) {
            //gaurdarPrueba(e+"");
                    
            System.out.println("Error en Delete usuario: " + e.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar usuario (cerrando conexión): " + e.getMessage());
            }
        }
    }
    
}
